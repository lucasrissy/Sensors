package com.example.sensor.mapper;

import com.example.sensor.dto.AuthenticatorDto;
import com.example.sensor.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

    public static User mapToEntity(AuthenticatorDto dto, User entity){


        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());

        return entity;
    }
}
