package com.example.demo2.service;

import com.example.demo2.model.Department;
import org.springframework.stereotype.Service;

@Service
public interface IDepartmentService {
    Department findById(Long id);

}
