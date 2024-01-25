package com.example.demo2.service.impl;

import com.example.demo2.model.Department;
import com.example.demo2.repository.IDepartmentRepository;
import com.example.demo2.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    IDepartmentRepository iDepartmentRepository;
    @Override
    public Department findById(Long id) {
        return iDepartmentRepository.findDepartmentById(id);
    }
}
