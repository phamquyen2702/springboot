package com.example.demo2.controller;

import com.example.demo2.common.CSVHelper;
import com.example.demo2.dto.response.ResponseMessage;
import com.example.demo2.model.Customer;
import com.example.demo2.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    ICustomerService iCustomerService;
    @PostMapping("/upload")
    public ResponseEntity<?> uploadCustomer(@RequestParam("file") MultipartFile file ) throws IOException {
        Iterable<Customer> customers = CSVHelper.csvToCustomers(file.getInputStream());
        iCustomerService.saveAll(customers);
        return new ResponseEntity<>(new ResponseMessage("Upload success!"), HttpStatus.OK);
    }
}
