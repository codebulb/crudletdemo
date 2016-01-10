package ch.codebulb.crudletdemo.server.webservice;

import ch.codebulb.crudlet.service.CrudService;
import ch.codebulb.crudlet.webservice.CrudResource;
import ch.codebulb.crudletdemo.server.model.Customer;
import ch.codebulb.crudletdemo.server.service.CustomerService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("customers")
@Stateless
public class CustomerResource extends CrudResource<Customer> {
    @Inject
    private CustomerService service;
    
    @Override
    protected CrudService<Customer> getService() {
        return service;
    }
}
