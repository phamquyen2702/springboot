package com.example.demo2.repository;

import com.example.demo2.dto.response.UserRoleReponse;
import com.example.demo2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findUserById(String id);
    @Query(value = "SELECT * FROM user WHERE department_id =?1",nativeQuery = true)
    Page<User> findByDepartmentId(Pageable pageable, Long id);

    @Query(value = "SELECT user_id FROM user_role WHERE role_id =?1", nativeQuery = true)
    Page<?> findByRoleId(Pageable pageable, Long id);

//    @Query(value = "UPDATE user SET department_id =?1 WHERE id =?2", nativeQuery = true)
//    User update(Long departmentId, String userId);
}
