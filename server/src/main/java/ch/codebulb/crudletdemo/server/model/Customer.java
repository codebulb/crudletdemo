package ch.codebulb.crudletdemo.server.model;

import ch.codebulb.crudlet.model.CrudIdentifiable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Customer implements CrudIdentifiable {
    // For use with MySQL, GenerationType.AUTO doesn't work: http://stackoverflow.com/a/4103347/1399395
    // TODO Build generic solution in CrudEntity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotEmpty
    @Pattern(regexp = "[A-Za-z ]*")
    private String name;
    private String address;
    private String city;
    
    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus = EmploymentStatus.Unemployed;
    private String companyName;
    
    // We don't cascade as child entities live independent of their parent
    /* 
     * Usually, we would have at least CascadeType.REMOVE in a 1..to..n relationship
     * such that child entities get removed when their parent is,
     * but for the sake of this demo application, we explicitly wish to cause
     * SQLIntegrityConstraintViolationExceptions when children are still present.
     */
    @OneToMany(mappedBy = "customer")
    private List<Payment> payments = new ArrayList<>();
    
    public Customer() {}

    @Deprecated
    // for demo purposes only
    public Customer(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }
    
    public Customer add(Payment payment) {
        this.payments.add(payment);
        payment.setCustomer(this);
        return this;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @XmlTransient
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
