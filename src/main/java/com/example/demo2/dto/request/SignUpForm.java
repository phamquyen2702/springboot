package com.example.demo2.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class SignUpForm {
    private String fullname;
    private String username;
    private String password;
    private String email;
    private Set<String> roles;

    public SignUpForm(String fullname, String username, String password, String email, Set<String> roles) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public SignUpForm() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
