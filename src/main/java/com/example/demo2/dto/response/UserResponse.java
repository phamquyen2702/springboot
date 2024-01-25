package com.example.demo2.dto.response;

import com.example.demo2.model.Role;
import com.example.demo2.model.User;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserResponse {
    private String id;

    private String fullname;

    private String username;

    private String email;

    private String departmentName;

    private List<String> roles;

    public UserResponse(String id, String fullname, String username, String email, String departmentName, List<String> roles) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.departmentName = departmentName;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public UserResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public UserResponse converter(UserResponse dto, User entity) {
        dto.setFullname(entity.getFullname());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setId(entity.getId());
        if(  entity.getDepartment() != null){
            dto.setDepartmentName(entity.getDepartment().getName());
        }
        List<String> listRole = new ArrayList<>();
        if(entity.getRoles() != null){
            for(Role role:entity.getRoles()){
                listRole.add(role.getName());
            }
        }
        dto.setRoles(listRole);
        return dto;
    }
}
