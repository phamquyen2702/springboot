package com.example.demo2.service.redis;

import com.example.demo2.dto.response.ProductResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductServiceRedis {
    void clear();
    List<ProductResponse> getAllProducts(Pageable pageable) throws JsonProcessingException;
    void saveAllProducts(List<ProductResponse> productResponses,Pageable pageable) throws JsonProcessingException;

}
