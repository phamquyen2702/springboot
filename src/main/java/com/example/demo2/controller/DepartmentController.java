package com.example.demo2.controller;

import com.example.demo2.dto.response.DepartmentReponse;
import com.example.demo2.dto.response.ResponseMessage;
import com.example.demo2.model.Department;
import com.example.demo2.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    IDepartmentService iDepartmentService;
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") Long id){
        Department department = iDepartmentService.findById(id);
        if(department == null){
            throw new RuntimeException("Not found with Id, please again");
        }
        DepartmentReponse departmentReponse = new DepartmentReponse();
        return  new ResponseEntity<>(departmentReponse.converter(departmentReponse,department).getUsers(),HttpStatus.OK);
    }
}
