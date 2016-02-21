package ch.codebulb.crudletdemo.server.webservice;

import ch.codebulb.crudlet.service.CrudService;
import ch.codebulb.crudlet.webservice.CrudResource;
import ch.codebulb.crudletdemo.server.model.Customer;
import ch.codebulb.crudletdemo.server.model.Payment;
import ch.codebulb.crudletdemo.server.service.CustomerService;
import ch.codebulb.crudletdemo.server.service.PaymentService;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("customers/{customerId}/payments")
@Stateless
public class PaymentResource extends CrudResource<Payment> {
    @Inject
    private PaymentService service;
    @Inject
    private CustomerService customerService;
    
    @Override
    protected CrudService<Payment> getService() {
        return service;
    }

    @Override
    protected List<Payment> findAllEntitiesBy(Map<String, String> queryParameters) {
        Long customerId = getPathParam("customerId", Long.class);
        return service.findBy(customerId, queryParameters);
    }

    @Override
    protected long countAllEntitiesBy(Map<String, String> queryParameters) {
        Long customerId = getPathParam("customerId", Long.class);
        return service.countBy(customerId, queryParameters);
    }
    
    @Override
    public Payment saveEntity(Payment entity) {
        Long customerId = getPathParam("customerId", Long.class);
        Customer customer = customerService.findById(customerId);
        entity.setCustomer(customer);
        return super.saveEntity(entity);
    }

    @Override
    protected void deleteAllEntitiesBy(Map<String, String> queryParameters) {
        Long customerId = getPathParam("customerId", Long.class);
        service.deleteBy(customerId, queryParameters);
    }
}
