package com.gizem.adminservice.entity;

public enum Role {
    USER, ADMIN;

    public static Role from(String input) {
        return Role.valueOf(input.trim().toUpperCase());
    }
}
