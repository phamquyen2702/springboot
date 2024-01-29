package com.example.demo2.service.redis.impl;

import com.example.demo2.dto.response.ProductResponse;
import com.example.demo2.service.redis.IProductServiceRedis;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceRedis implements IProductServiceRedis {
    @Autowired
    private  RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ObjectMapper redisObjectMapper;

    private String getKeyFrom(Pageable pageable) {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        String key = String.format("all_products:%d:%d", page, size);
        return key;
    }

    @Override
    public void clear() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Override
    public List<ProductResponse> getAllProducts(Pageable pageable) throws JsonProcessingException {
        String key = this.getKeyFrom(pageable);
        String json = (String) redisTemplate.opsForValue().get(key);
        List<ProductResponse> productResponses =
                json != null ? redisObjectMapper.readValue(json, new TypeReference<List<ProductResponse>>() {})
                        : null;
        return productResponses;
    }

    //save to redis
    @Override
    public void saveAllProducts(List<ProductResponse> productResponses, Pageable pageable) throws JsonProcessingException {
        String key = this.getKeyFrom(pageable);
        String json = redisObjectMapper.writeValueAsString(productResponses);
        redisTemplate.opsForValue().set(key, json);
    }
}
