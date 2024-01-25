package com.example.demo2.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String Address;
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<CustomerProducts> customerProductsList;

    public Customer() {
    }

    public Customer(long id, String name, String address, String phone, List<CustomerProducts> customerProductsList) {
        this.id = id;
        this.name = name;
        Address = address;
        this.phone = phone;
        this.customerProductsList = customerProductsList;
    }
    public Customer(String name, String address, String phone) {
        this.name = name;
        Address = address;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CustomerProducts> getCustomerProductsList() {
        return customerProductsList;
    }

    public void setCustomerProductsList(List<CustomerProducts> customerProductsList) {
        this.customerProductsList = customerProductsList;
    }
}
