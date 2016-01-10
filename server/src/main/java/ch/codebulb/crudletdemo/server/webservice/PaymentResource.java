package ch.codebulb.crudletdemo.server.webservice;

import ch.codebulb.crudlet.service.CrudService;
import ch.codebulb.crudlet.webservice.CrudResource;
import ch.codebulb.crudletdemo.server.model.Customer;
import ch.codebulb.crudletdemo.server.model.Payment;
import ch.codebulb.crudletdemo.server.service.CustomerService;
import ch.codebulb.crudletdemo.server.service.PaymentService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("customers/{customerId}/payments")
@Stateless
public class PaymentResource extends CrudResource<Payment> {
    @Inject
    private PaymentService service;
    @Inject
    CustomerService customerService;
    
    @Override
    protected CrudService<Payment> getService() {
        return service;
    }

    @Override
    public List<Payment> findAll() {
        Long customerId = getPathParam("customerId", Long.class);
        return service.findAllByCustomer(customerId);
    }
    
    @Override
    public Response save(Payment entity) {
        Long customerId = getPathParam("customerId", Long.class);
        Customer customer = customerService.findById(customerId);
        entity.setCustomer(customer);
        return super.save(entity);
    }
}
