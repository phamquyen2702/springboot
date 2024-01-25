package com.example.demo2.dto.response;

import com.example.demo2.model.Role;
import com.example.demo2.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponseNCT{
    private String id;

    private String fullname;

    private String username;

    private String email;


    private List<String> roles;

    public UserResponseNCT(String id, String fullname, String username, String email, String departmentName, List<String> roles) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public UserResponseNCT() {
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



    public UserResponseNCT converter(UserResponseNCT dto, User entity) {
        dto.setFullname(entity.getFullname());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setId(entity.getId());
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
