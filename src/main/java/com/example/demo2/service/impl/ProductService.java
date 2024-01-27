package com.example.demo2.service.impl;

import com.example.demo2.model.Product;
import com.example.demo2.repository.IProductRepository;
import com.example.demo2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return iProductRepository.findAll( pageable);
    }
}
