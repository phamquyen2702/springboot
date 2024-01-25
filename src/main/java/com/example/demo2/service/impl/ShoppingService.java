package com.example.demo2.service.impl;

import com.example.demo2.model.CustomerProducts;
import com.example.demo2.repository.IShoppingRepository;
import com.example.demo2.service.IShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingService implements IShoppingService {

    @Autowired
    IShoppingRepository iShoppingRepository;

    @Override
    public CustomerProducts save(CustomerProducts customerProducts) {
        return iShoppingRepository.save(customerProducts);
    }

    @Override
    public CustomerProducts updateTotal(int total, Long customerId, Long productId) {

        CustomerProducts customerProducts1 = iShoppingRepository.findByCustomerIdAndProductId(customerId, productId);

        if (customerProducts1 != null) {
            if (total == 0) {
                iShoppingRepository.delete(customerProducts1);
            } else {
                customerProducts1.setTotal(total);
                return iShoppingRepository.save(customerProducts1);
            }

        }
        return null;
    }

    @Override
    public boolean existsCustomerIdAndProductId(Long customerId, Long productId) {
        return iShoppingRepository.existsByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    public CustomerProducts findByCustomerIdAndProductId(Long customerId, Long productId) {
        return iShoppingRepository.findByCustomerIdAndProductId(customerId,productId);
    }
}
