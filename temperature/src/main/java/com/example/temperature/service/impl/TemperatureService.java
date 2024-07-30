package com.example.temperature.service.impl;

import com.example.temperature.dto.TemperatureSensorDto;
import com.example.temperature.exceptions.ResourceNotFound;
import com.example.temperature.mapper.TemperatureMapper;
import com.example.temperature.repository.TemperatureRepository;
import com.example.temperature.service.TemperatureServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TemperatureService implements TemperatureServiceInterface {

    private TemperatureRepository repository;

    private TemperatureMapper mapper;


    @Override
    public TemperatureSensorDto fetchTemperatureById(Long id) {

        return TemperatureMapper.mapToDto( new TemperatureSensorDto(),repository.findById(id)
                .orElseThrow( () -> new ResourceNotFound("Sensor not found","id",id.toString())));
    }
}
