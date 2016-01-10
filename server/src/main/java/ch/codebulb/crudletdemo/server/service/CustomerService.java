package ch.codebulb.crudletdemo.server.service;

import ch.codebulb.crudlet.service.CrudService;
import ch.codebulb.crudletdemo.server.model.Customer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomerService extends CrudService<Customer> {
    @Override
    @PersistenceContext
    protected void setEm(EntityManager em) {
        super.setEm(em);
    }
    
    @Override
    public Customer create() {
        return new Customer();
    }

    @Override
    public Class<Customer> getModelClass() {
        return Customer.class;
    }
}
