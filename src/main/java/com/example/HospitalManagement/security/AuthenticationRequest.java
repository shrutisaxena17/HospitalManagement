package com.example.HospitalManagement.security;

public class AuthenticationRequest {
    private String userId;
    private String password;

    // Getters and Setters
    public String getUsername() {
        return userId;
    }

    public void setUsername(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}