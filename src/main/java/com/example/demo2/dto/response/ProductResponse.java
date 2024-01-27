package com.example.demo2.dto.response;

import com.example.demo2.model.Product;

public class ProductResponse {
    private Long id;
    private String title;
    private String detail;
    private Long pricebuy;
    private Long pricesell;
    private int total;
    private String images;

    public ProductResponse(Long id, String title, String detail, Long pricebuy, Long pricesell, int total, String images) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.pricebuy = pricebuy;
        this.pricesell = pricesell;
        this.total = total;
        this.images = images;
    }

    public ProductResponse() {
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

    public ProductResponse converter(ProductResponse dto, Product entity){
        dto.setId(entity.getId());
        dto.setImages(entity.getImages());
        dto.setDetail(entity.getDetail());
        dto.setPricebuy(entity.getPricebuy());
        dto.setPricesell(entity.getPricesell());
        dto.setTitle(entity.getTitle());
        dto.setTotal(entity.getTotal());
        return dto;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", pricebuy=" + pricebuy +
                ", pricesell=" + pricesell +
                ", total=" + total +
                ", images='" + images + '\'' +
                '}';
    }
}
