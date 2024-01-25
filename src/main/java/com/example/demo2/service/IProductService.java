package com.example.demo2.service;


import com.example.demo2.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface IProductService  {
    boolean existsById(Long id);
    Product findProductById(Long id);

    Product save(Product product);
}
