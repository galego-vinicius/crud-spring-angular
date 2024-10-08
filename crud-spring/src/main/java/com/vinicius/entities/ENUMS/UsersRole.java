package com.vinicius.entities.ENUMS;

public enum UsersRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UsersRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
