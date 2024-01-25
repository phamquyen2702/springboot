package com.example.demo2.service;

import com.example.demo2.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface ICustomerService {
    boolean existsById(Long id);
    Customer findCustomerById(Long id);

    Iterable<Customer> saveAll(Iterable<Customer> customers);

}
