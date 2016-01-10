package ch.codebulb.crudletdemo.server.webservice;

import ch.codebulb.crudlet.model.RestfulPersistenceConstraintViolationException;
import ch.codebulb.crudletdemo.server.model.Customer;
import ch.codebulb.crudletdemo.server.model.Payment;
import ch.codebulb.crudletdemo.server.service.CustomerService;
import ch.codebulb.crudletdemo.server.service.PaymentService;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
@Deprecated
// for demo purposes only
public class ResourceDemoInitializer {
    @Inject
    private CustomerService customerService;
    @Inject
    private PaymentService paymentService;
    
    @PostConstruct
    protected void initDemoData() {
        try {
            Customer customer1 = new Customer("Max", "First Street", "Los Angeles");
            customer1 = customerService.save(customer1);
            
            Payment payment11 = new Payment(100, new Date(), customer1);
            paymentService.save(payment11);
            Payment payment12 = new Payment(200, null, customer1);
            paymentService.save(payment12);
            
            Customer customer2 = new Customer("Sarah", "Second Street", "San Francisco");
            customer2 = customerService.save(customer2);
            
            Payment payment21 = new Payment(100, new Date(), customer2);
            paymentService.save(payment21);
        } catch (RestfulPersistenceConstraintViolationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
