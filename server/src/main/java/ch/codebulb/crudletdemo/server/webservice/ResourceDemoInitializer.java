package ch.codebulb.crudletdemo.server.webservice;

import ch.codebulb.crudlet.config.RestfulExceptionMapper;
import ch.codebulb.crudlet.model.errors.RestfulPersistenceValidationConstraintViolation;
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
        // for demo purposes only:
        /* reuse formerly inserted DB values (from the equivalent Node.js server startup run).
         * this is effective only if in persistence.xml:
         * <property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
         * is *not* set.
         */
        if (!customerService.findAll().isEmpty()) {
            return;
        }
        
        Customer customer1 = new Customer("Max", "First Street", "Los Angeles");
        customer1 = customerService.save(customer1);

        Payment payment11 = new Payment(100, new Date());
        customer1.add(payment11);
        paymentService.save(payment11);
        Payment payment12 = new Payment(200, null);
        customer1.add(payment12);
        paymentService.save(payment12);
        
        customerService.save(customer1);

        Customer customer2 = new Customer("Sarah", "Second Street", "San Francisco");
        customer2 = customerService.save(customer2);

        Payment payment21 = new Payment(100, new Date());
        customer2.add(payment21);
        paymentService.save(payment21);
        
        customerService.save(customer2);
    }
}
