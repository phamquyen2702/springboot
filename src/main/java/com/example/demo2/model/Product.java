package com.example.demo2.model;

import com.example.demo2.listener.ProductListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@EntityListeners(ProductListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String detail;
    private Long pricebuy;
    private Long pricesell;
    private int total;
    @Lob
    private String images;

    private
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    List<CustomerProducts> customerProductsList;

    public Product() {
    }

    public Product(Long id, String title, String detail, Long pricebuy, Long pricesell, int total, String images, List<CustomerProducts> customerProductsList) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.pricebuy = pricebuy;
        this.pricesell = pricesell;
        this.total = total;
        this.images = images;
        this.customerProductsList = customerProductsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getPricebuy() {
        return pricebuy;
    }

    public void setPricebuy(Long pricebuy) {
        this.pricebuy = pricebuy;
    }

    public Long getPricesell() {
        return pricesell;
    }

    public void setPricesell(Long pricesell) {
        this.pricesell = pricesell;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<CustomerProducts> getCustomerProductsList() {
        return customerProductsList;
    }

    public void setCustomerProductsList(List<CustomerProducts> customerProductsList) {
        this.customerProductsList = customerProductsList;
    }
}
