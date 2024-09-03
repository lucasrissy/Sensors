package com.example.sensor.entity;

import lombok.Getter;

@Getter
public enum RoleEnum {

    ADMIN("admin"),
    OPERATOR("user");

    private String role;

    RoleEnum(String role){
        this.role = role;
    }

}
