package com.example.demo2.service.impl;

import com.example.demo2.model.Customer;
import com.example.demo2.repository.ICustomerRepository;
import com.example.demo2.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository iCustomerRepository;
    @Override
    public boolean existsById(Long id) {
        return iCustomerRepository.existsById(id);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return iCustomerRepository.findCustomerById(id);
    }

    @Override
    public Iterable<Customer> saveAll(Iterable<Customer> customers) {
        return iCustomerRepository.saveAll(customers);
    }
}
