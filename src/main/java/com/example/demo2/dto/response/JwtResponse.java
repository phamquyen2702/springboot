package com.example.demo2.dto.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private String id;
    private String token;
    private String type = "Bearer";
    private String fullname;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(String token, String fullname, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.fullname = fullname;
        this.roles = authorities;
    }

    public JwtResponse(String id, String token, String type, String fullname, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.fullname = fullname;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
