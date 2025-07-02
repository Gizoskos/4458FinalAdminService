package com.gizem.adminservice.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    public AuthResponse(String token, String userId, String role) {
        this.token = token;
        this.userId = userId;
        this.role = role;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}