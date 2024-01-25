package com.example.demo2.controller;

import com.example.demo2.dto.response.ResponseMessage;
import com.example.demo2.model.CustomerProducts;
import com.example.demo2.service.ICustomerService;
import com.example.demo2.service.IProductService;
import com.example.demo2.service.IShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    IProductService iProductService;

    @Autowired
    IShoppingService iShoppingService;
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam("customerId") Long customerId, @RequestParam("productId") Long productId) {
        if(!iCustomerService.existsById(customerId)){
            throw new RuntimeException("Not found customer, please try again!");
        }
        if(!iProductService.existsById(productId)){
            throw new RuntimeException("Not found product, please try again!");
        }
        if(iShoppingService.existsCustomerIdAndProductId(customerId,productId)){
            CustomerProducts customerProducts = iShoppingService.findByCustomerIdAndProductId(customerId,productId);
            int total = customerProducts.getTotal() + 1;
            iShoppingService.updateTotal(total,customerId,productId);
            return new ResponseEntity<>(new ResponseMessage("Update success"), HttpStatus.OK);
        }
        CustomerProducts customerProducts = new CustomerProducts();
        customerProducts.setTotal(1);
        customerProducts.setDatebuy(new Date());
        customerProducts.setStatus(true);//order type
        customerProducts.setCustomer(iCustomerService.findCustomerById(customerId));
        customerProducts.setProduct(iProductService.findProductById(productId));
        iShoppingService.save(customerProducts);
        return new ResponseEntity<>(new ResponseMessage("Add success"), HttpStatus.OK);
    }
}
