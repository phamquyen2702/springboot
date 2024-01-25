package com.example.demo2.service;

import com.example.demo2.dto.response.UserResponse;
import com.example.demo2.dto.response.UserRoleReponse;
import com.example.demo2.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface IUserService {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User save(User user);
    List<User> getAll();
    boolean deleteUser(String id);
    User updateUser(String id, User user);
    User getUserById(String id);

    boolean existsById(String id);

    List<User> getByDepartmentId(Long id, int page, int size);

    List<UserResponse> getByRoleId(Long id, int page, int size);

    User save(Long departmentId, String userId);
}
