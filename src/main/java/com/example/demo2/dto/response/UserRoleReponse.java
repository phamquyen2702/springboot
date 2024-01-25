package com.example.demo2.dto.response;

public class UserRoleReponse {
    private String userId;
    private Long roleId;

    public UserRoleReponse() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public UserRoleReponse(String userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
