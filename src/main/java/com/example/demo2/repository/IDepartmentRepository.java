package com.example.demo2.repository;

import com.example.demo2.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department,Long> {
    Department findDepartmentById(Long id);
}
