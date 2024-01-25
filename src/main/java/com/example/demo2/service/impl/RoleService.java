package com.example.demo2.service.impl;

import com.example.demo2.model.Role;
import com.example.demo2.repository.IRoleRepository;
import com.example.demo2.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService implements IRoleService {
    @Autowired
    IRoleRepository iRoleRepository;
    @Override
    public Optional<Role> findByName(String name) {
        return iRoleRepository.findByName(name);
    }
}
