package com.example.sensor.dto;

import com.example.sensor.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticatorDto {

    private String username;

    private String password;

    private RoleEnum role;
}
