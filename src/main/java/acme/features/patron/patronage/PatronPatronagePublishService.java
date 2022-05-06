package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronagePublishService implements AbstractUpdateService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	 
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		
		final int id = request.getModel().getInteger("id");
		final Patronage patronage = this.repository.findOnePatronage(id);
		
		result = request.getPrincipal().hasRole(Patron.class) && patronage.isNotPublished();
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        request.bind(entity, errors, "legalStuff", "budget", "startPeriod", "endPeriod", "link");
		
	}
	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        if(!errors.hasErrors("budget")) {
        	final String acceptedCurrencies = this.repository.findConfiguration().getAcceptedCurrencies();
            final String[] currencies = acceptedCurrencies.split(",");
            boolean isCorrect = false;
            final String c = entity.getBudget().getCurrency();
            for (final String currency : currencies) {
            	if (c.equals(currency)) {
            		isCorrect = true;
            	}
			}
        	errors.state(request, isCorrect, "budget", "patron.patronage.form.error.incorrect-currency");
        }
        
        if(!errors.hasErrors("startPeriod")) {
        	final Date startPeriod = entity.getStartPeriod();
        	final Calendar calendar = Calendar.getInstance();
        	final Date creationDate = entity.getCreationDate();
        	calendar.setTime(creationDate); // Aquí no tendremos en cuenta la fecha de actualización, sino de creación
        	calendar.add(Calendar.MONTH, 1);
        	calendar.add(Calendar.SECOND, -1); // Un mes menos un segundo
        	errors.state(request, startPeriod.after(calendar.getTime()), "startPeriod", "patron.patronage.form.error.start-period-not-enough");
        }
        
        if(!errors.hasErrors("endPeriod")) {
        	final Date startPeriod = entity.getStartPeriod();
        	final Date endPeriod = entity.getEndPeriod();
        	final Date moment = new Date(startPeriod.getTime() + 604799999); // Una semana menos un milisegundo
        	errors.state(request, endPeriod.after(moment), "endPeriod", "patron.patronage.form.error.end-period-one-week-before-start-period");
        }
        
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "legalStuff", "budget", "startPeriod", "endPeriod", "link", "notPublished");
		
		model.setAttribute("confirmation", false);
		
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronage(id);
		
		return result;
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
        assert entity != null;
        
        entity.setNotPublished(false);

        this.repository.save(entity);
	}
	
}