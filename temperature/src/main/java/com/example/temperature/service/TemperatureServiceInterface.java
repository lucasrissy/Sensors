package com.example.temperature.service;

import com.example.temperature.dto.TemperatureSensorDto;
import com.example.temperature.mapper.TemperatureMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemperatureServiceInterface {

    TemperatureSensorDto fetchTemperatureById(Long id);

    Page<TemperatureSensorDto> pageAllSensors(Pageable pageable);

    TemperatureSensorDto registry(TemperatureSensorDto sensorDto);

    boolean updateSensor (TemperatureSensorDto dto,Long id);

    boolean deleteSensor(Long id);
}
