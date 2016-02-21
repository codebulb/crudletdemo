package ch.codebulb.crudletdemo.server.model;

import ch.codebulb.crudlet.model.CrudIdentifiable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@NamedQueries({
    @NamedQuery(name = Payment.FIND_BY_CUSTOMER_ID, query = "SELECT e FROM Payment e where e.customer.id = :customerId"),
    @NamedQuery(name = Payment.DELETE_BY_CUSTOMER_ID, query = "DELETE FROM Payment e where e.customer.id = :customerId"),
})
public class Payment implements CrudIdentifiable {
    public static final String FIND_BY_CUSTOMER_ID = "Payment.findByCustomerId";
    public static final String DELETE_BY_CUSTOMER_ID = "Payment.deleteByCustomerId";
    
    // For use with MySQL, GenerationType.AUTO doesn't work: http://stackoverflow.com/a/4103347/1399395
    // TODO Build generic solution in CrudEntity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @Min(1)
    private int amount;
    @Temporal(TemporalType.DATE)
    @XmlJavaTypeAdapter(DateFormatterAdapter.class)
    private Date date;
    @JoinColumn(nullable = false)
    @NotNull
    @ManyToOne(optional = false)
    private Customer customer;

    private static class DateFormatterAdapter extends XmlAdapter<String, Date> {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public Date unmarshal(final String date) throws Exception {
            return format.parse(date);
        }

        @Override
        public String marshal(Date date) throws Exception {
            return format.format(date);
        }
    }
    
    public Payment() {}

    @Deprecated
    // for demo purposes only
    public Payment(int amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
