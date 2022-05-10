package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Status;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageAcceptOrDenyService implements AbstractUpdateService<Inventor, Patronage>{
	
	@Autowired
	protected InventorPatronageRepository repository;
	 
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		
		final int id = request.getModel().getInteger("id");
		final Patronage patronage = this.repository.findOnePatronage(id);
		
		result = request.getPrincipal().hasRole(Inventor.class) && patronage.getStatus().equals(Status.PROPOSED);
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        request.bind(entity, errors, "status");
		
	}
	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "startPeriod", "endPeriod", "link");
		
		final boolean proposed = entity.getStatus().equals(Status.PROPOSED);
		model.setAttribute("proposed", proposed);
		
		final String fullName = entity.getPatron().getUserAccount().getIdentity().getFullName();
		model.setAttribute("fullName", fullName);
		
		final String email = entity.getPatron().getUserAccount().getIdentity().getEmail();
		model.setAttribute("email", email);
		
		model.setAttribute("readonly", false);
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

        this.repository.save(entity);
	}
	
}
