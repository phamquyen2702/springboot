package com.example.demo2.service.impl;

import com.example.demo2.model.Product;
import com.example.demo2.repository.IProductRepository;
import com.example.demo2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository iProductRepository;
    @Override
    public boolean existsById(Long id) {
        return iProductRepository.existsById(id);
    }

    @Override
    public Product findProductById(Long id) {
        return iProductRepository.findProductById(id);
    }

    @Override
    public Product save(Product product) {
        return iProductRepository.save(product);
    }
}
