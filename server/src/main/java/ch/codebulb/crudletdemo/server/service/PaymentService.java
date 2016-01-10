package ch.codebulb.crudletdemo.server.service;

import ch.codebulb.crudlet.service.CrudService;
import ch.codebulb.crudletdemo.server.model.Payment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PaymentService extends CrudService<Payment> {
    @Override
    @PersistenceContext
    protected void setEm(EntityManager em) {
        super.setEm(em);
    }
    
    @Override
    public Payment create() {
        return new Payment();
    }

    @Override
    public Class<Payment> getModelClass() {
        return Payment.class;
    }

    public List<Payment> findAllByCustomer(Long customerId) {
        return em.createNamedQuery(Payment.FIND_BY_CUSTOMER_ID, Payment.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }
    
    
}
