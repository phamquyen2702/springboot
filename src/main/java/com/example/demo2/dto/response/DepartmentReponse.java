package com.example.demo2.dto.response;

import com.example.demo2.model.Department;
import com.example.demo2.model.User;

import java.util.ArrayList;
import java.util.List;

public class DepartmentReponse {
    private Long id;
    private String name;

    private String layer;
    private List<UserResponseNCT> users;

    public DepartmentReponse() {
    }

    public DepartmentReponse(Long id, String name, String layer, List<UserResponseNCT> users) {
        this.id = id;
        this.name = name;
        this.layer = layer;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserResponseNCT> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponseNCT> users) {
        this.users = users;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public DepartmentReponse converter(DepartmentReponse dto, Department entity) {
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLayer(entity.getLayer());

        List<UserResponseNCT> userResponseList = new ArrayList<>();
        for (User user : entity.getUsers()) {
            UserResponseNCT userResponseNCT = new UserResponseNCT();
            userResponseList.add(userResponseNCT.converter(userResponseNCT, user));
        }
        dto.setUsers(userResponseList);
        return dto;
    }
}
