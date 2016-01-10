package ch.codebulb.crudletdemo.server.model;

import ch.codebulb.crudlet.model.CrudEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Customer extends CrudEntity { 
    @NotNull
    @Pattern(regexp = "[A-Za-z ]*")
    private String name;
    private String address;
    private String city;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private EmploymentStatus employmentStatus = EmploymentStatus.Unemployed;
    private String companyName;
    
    // We don't cascade as child entities live independent of their parent
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
    
    public boolean isCompanyEmptyWhenUnemployed() {
        if (getEmploymentStatus() == EmploymentStatus.Unemployed) {
            return getCompanyName() == null;
        }
        return true;
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
