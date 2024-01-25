package com.example.demo2.service.impl;

import com.example.demo2.dto.response.UserResponse;
import com.example.demo2.model.Department;
import com.example.demo2.model.User;
import com.example.demo2.repository.IDepartmentRepository;
import com.example.demo2.repository.IUserRepository;
import com.example.demo2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    IDepartmentRepository iDepartmentRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return iUserRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return iUserRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return iUserRepository.findAll();
    }

    @Override
    public boolean deleteUser(String id) {
        boolean existStatus = iUserRepository.existsById(id);
        if (existStatus) {
            iUserRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User updateUser(String id, User user) {
        return null;
    }

    @Override
    public User getUserById(String id) {
        return iUserRepository.findUserById(id);
    }

    @Override
    public boolean existsById(String id) {
        return iUserRepository.existsById(id);
    }

    @Override
    public List<User> getByDepartmentId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> pages = iUserRepository.findByDepartmentId(pageable, id);
        return pages.stream().toList();
    }

    @Override
    public List<UserResponse> getByRoleId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<?> pages = iUserRepository.findByRoleId(pageable, id);
        List<UserResponse> userResponseList = new ArrayList<>();
        for (Object ignored : pages) {
            User user = iUserRepository.findUserById(String.valueOf(ignored));
            UserResponse userResponse = new UserResponse();
            userResponse.converter(userResponse, user);
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    @Override
    public User save(Long departmentId, String userId) {
        User user = iUserRepository.findUserById(userId);
        if (user != null) {
            Department department = iDepartmentRepository.findDepartmentById(departmentId);
            user.setDepartment(department);
            return iUserRepository.save(user);
        }
        return null;
    }
}
