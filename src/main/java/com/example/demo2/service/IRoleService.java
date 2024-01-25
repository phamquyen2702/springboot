package com.example.demo2.service;

import com.example.demo2.model.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface IRoleService {
    Optional<Role> findByName(String name);
}
