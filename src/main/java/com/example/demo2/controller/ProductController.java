package com.example.demo2.controller;

import com.example.demo2.dto.response.ProductResponse;
import com.example.demo2.dto.response.ResponseMessage;
import com.example.demo2.model.Product;
import com.example.demo2.service.IProductService;
import com.example.demo2.service.redis.IProductServiceRedis;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    IProductService iProductService;
    @Autowired
    IProductServiceRedis iProductServiceRedis;

    @GetMapping("/get")
    public ResponseEntity<List<?>> getAllProduct(@RequestParam("page") int page, @RequestParam("size") int size) throws JsonProcessingException {
        if (page < 0 || size <= 0) {
            throw new RuntimeException("page or size not exists");
        }
        Pageable pageable = PageRequest.of(page, size);

        List<ProductResponse> productResponseList = iProductServiceRedis.getAllProducts(pageable);
        if (productResponseList != null) {
            logger.info("get data from redis");
            return new ResponseEntity<>(productResponseList, HttpStatus.OK);
        } else {
            Page<Product> products = iProductService.findAll(pageable);
            List<ProductResponse> productResponses = new ArrayList<>();
            for (Product product : products) {
                ProductResponse productResponse = new ProductResponse();
                productResponses.add(productResponse.converter(productResponse, product));
            }
            logger.info("get data from db, save item into redis");
            iProductServiceRedis.saveAllProducts(productResponses, pageable);
            return new ResponseEntity<>(productResponses, HttpStatus.OK);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Product product){
        iProductService.save(product);
        return new ResponseEntity<>(new ResponseMessage("Add success"), HttpStatus.OK);
    }
}
