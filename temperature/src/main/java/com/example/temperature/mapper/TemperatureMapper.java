package com.example.temperature.mapper;

import com.example.temperature.dto.TemperatureSensorDto;
import com.example.temperature.entity.TemperatureSensor;
import org.springframework.stereotype.Component;

@Component
public class TemperatureMapper {

    public static TemperatureSensor mapToEntity(TemperatureSensorDto dto, TemperatureSensor entity){

        entity.setTemperature(dto.getTemperature());
        entity.setTimestamp(dto.getTimestamp());
        entity.setId(dto.getId());

        return entity;
    }

    public static TemperatureSensorDto mapToDto(TemperatureSensorDto dto, TemperatureSensor entity){

        dto.setId(entity.getId());
        dto.setTemperature(entity.getTemperature());
        dto.setTimestamp(entity.getTimestamp());

        return dto;
    }
}
