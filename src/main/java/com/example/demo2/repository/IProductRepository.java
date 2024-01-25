package com.example.demo2.repository;

import com.example.demo2.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsById(@NotNull Long id);
    Product findProductById(Long id);
}
