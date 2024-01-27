package com.example.demo2.listener;

import com.example.demo2.model.Product;
import com.example.demo2.service.redis.IProductServiceRedis;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
public class ProductListener {

    public static final Logger logger = LoggerFactory.getLogger(ProductListener.class);
    @Autowired
    IProductServiceRedis iProductServiceRedis;

    @PrePersist
    public void prePersist(Product product) {
        logger.info("not action");
    }

    @PostPersist
    public void lastPersist(Product product) {
        iProductServiceRedis.clear();
        logger.info("clear redis");
    }

    @PreUpdate
    public void preUpdate(Product product) {
        logger.info("not action");
    }

    @PostUpdate
    public void lastUpdate(Product product) {
        iProductServiceRedis.clear();
        logger.info("clear redis");
    }

    @PreRemove
    public void preRemove(Product product) {
        logger.info("not action");
    }

    @PostRemove
    public void lastRemove(Product product) {
        iProductServiceRedis.clear();
        logger.info("clear redis");
    }

}
