package com.example.demo2.repository;

import com.example.demo2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsById(Long id);
    Customer findCustomerById(Long id);
}
