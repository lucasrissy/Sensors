package com.example.temperature.mapper;

import com.example.temperature.dto.TemperatureSensorDto;
import com.example.temperature.entity.Temperature;
import org.springframework.stereotype.Component;

@Component
public class TemperatureMapper {

    public static Temperature mapToEntity(TemperatureSensorDto dto, Temperature entity){

        entity.setId(dto.getUID());
        entity.setTemperature(dto.getTemperature());
        entity.setTimestamp(dto.getTimestamp());

        return entity;
    }

    public static TemperatureSensorDto mapToDto(TemperatureSensorDto dto, Temperature entity){

        dto.setUID(entity.getId());
        dto.setTemperature(entity.getTemperature());
        dto.setTimestamp(entity.getTimestamp());

        return dto;
    }
}
