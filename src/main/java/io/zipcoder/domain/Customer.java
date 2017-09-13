package io.zipcoder.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import java.util.Set;

@Entity

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUSTOMER_ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String first_name;
    
    @Column(name = "LAST_NAME")
    private String last_name;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    @OrderBy
    private Set<Address> address;

    public long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address){
        this.address = address;
    }

}
