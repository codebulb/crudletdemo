package ch.codebulb.crudletdemo.server.service;

import ch.codebulb.crudlet.service.CrudService;
import ch.codebulb.crudletdemo.server.model.Payment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public List<Payment> findBy(Long customerId, Map<String, String> predicates) {
        if (predicates == null) {
            predicates = new HashMap<>();
        }
        predicates.put("customerId", customerId.toString());
        return super.findBy(predicates);
    }
    
    public long countBy(Long customerId, Map<String, String> predicates) {
        if (predicates == null) {
            predicates = new HashMap<>();
        }
        predicates.put("customerId", customerId.toString());
        return super.countBy(predicates);
    }
    
    public void deleteBy(Long customerId, Map<String, String> predicates) {
        if (predicates == null) {
            predicates = new HashMap<>();
        }
        predicates.put("customerId", customerId.toString());
        super.deleteBy(predicates);
    }
}
