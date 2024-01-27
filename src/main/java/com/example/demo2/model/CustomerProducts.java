package com.example.demo2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_products")
public class CustomerProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datebuy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetrade;
    private boolean status = true;

    private int total;

    public CustomerProducts() {
    }

    public CustomerProducts(Long id, Customer customer, Product product, Date datebuy, Date datetrade, boolean status, int total) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.datebuy = datebuy;
        this.datetrade = datetrade;
        this.status = status;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDatebuy() {
        return datebuy;
    }

    public void setDatebuy(Date datebuy) {
        this.datebuy = datebuy;
    }

    public Date getDatetrade() {
        return datetrade;
    }

    public void setDatetrade(Date datetrade) {
        this.datetrade = datetrade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
