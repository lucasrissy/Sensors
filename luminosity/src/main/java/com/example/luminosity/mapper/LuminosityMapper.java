package com.example.luminosity.mapper;

import com.example.luminosity.dto.LuminosityDto;
import com.example.luminosity.entity.Luminosity;
import org.springframework.stereotype.Component;

@Component
public class LuminosityMapper {

    public static Luminosity mapToEntity (Luminosity entity, LuminosityDto dto){

        entity.setLuminosity(dto.getLuminosity());
        entity.setId(dto.getUID());
        entity.setTimestamp(dto.getTimestamp());

        return entity;
    }

    public static LuminosityDto mapToDto (Luminosity entity, LuminosityDto dto){

        dto.setLuminosity(entity.getLuminosity());
        dto.setUID(entity.getId());
        dto.setTimestamp(entity.getTimestamp());

        return dto;
    }
}
