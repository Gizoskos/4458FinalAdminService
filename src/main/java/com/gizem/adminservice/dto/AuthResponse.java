package com.gizem.adminservice.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    public AuthResponse(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}