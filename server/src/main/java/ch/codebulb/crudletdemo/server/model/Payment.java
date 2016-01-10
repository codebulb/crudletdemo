package ch.codebulb.crudletdemo.server.model;

import ch.codebulb.crudlet.model.CrudEntity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@NamedQuery(name = Payment.FIND_BY_CUSTOMER_ID, query = "SELECT e FROM Payment e where e.customer.id = :customerId")
@XmlRootElement
public class Payment extends CrudEntity {
    public static final String FIND_BY_CUSTOMER_ID = "Payment.findByCustomerId";
    
    @NotNull
    @Min(1)
    private int amount;
    @Temporal(TemporalType.DATE)
    @XmlJavaTypeAdapter(DateFormatterAdapter.class) 
    private Date date;
    @NotNull
    @ManyToOne(optional = false)
    private Customer customer;
    
    public Payment() {}

    public Payment(Customer customer) {
        this.customer = customer;
    }
    
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

    @Deprecated
    // for demo purposes only
    public Payment(int amount, Date date, Customer customer) {
        this.amount = amount;
        this.date = date;
        this.customer = customer;
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
    
    public Long getCustomerId() {
        if (getCustomer() == null) {
            return null;
        }
        return getCustomer().getId();
    }
}
