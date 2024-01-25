package com.example.demo2.service;

import com.example.demo2.model.CustomerProducts;
import org.springframework.stereotype.Service;

@Service
public interface IShoppingService {
    CustomerProducts save(CustomerProducts customerProducts);

    CustomerProducts updateTotal(int total, Long customerId, Long productId);

    boolean existsCustomerIdAndProductId(Long customerId, Long ProductId);

    CustomerProducts findByCustomerIdAndProductId(Long customerId, Long productId);
}
