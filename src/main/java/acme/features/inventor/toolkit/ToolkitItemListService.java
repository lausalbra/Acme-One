package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service 
public class ToolkitItemListService implements AbstractListService<Inventor, Item>{

    @Autowired
    protected InventorToolkitRepository repository;

    @Override
    public boolean authorise(final Request<Item> request) {
        assert request != null; 

        return true; 
    }

    @Override
    public Collection<Item> findMany(final Request<Item> request) {
        final Collection<Item> result = new HashSet<>();
        int toolkitId;

        toolkitId = request.getModel().getInteger("id");
        final Collection<Quantity> quantities = this.repository.findManyQuantitiesByToolkitId(toolkitId);

        for(final Quantity quantity: quantities) {
            final int id=quantity.getId();
            final Collection<Item> items=this.repository.findManyItemsByQuantityId(id);
            result.addAll(items);
        }

        return result;
    }

    @Override
    public void unbind(final Request<Item> request, final Item entity, final Model model) {
        assert request != null; 
        assert entity != null; 
        assert model != null; 

        request.unbind(entity, model, "itemType", "name","code", "technology", "retailPrice"); 

    }

}