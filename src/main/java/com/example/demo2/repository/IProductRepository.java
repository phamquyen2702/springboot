package com.example.demo2.repository;

import com.example.demo2.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsById( Long id);
    Product findProductById(Long id);
    Page<Product> findAll(Pageable pageable);
}
