package com.example.temperature.service;

import com.example.temperature.dto.TemperatureSensorDto;

public interface TemperatureServiceInterface {

    TemperatureSensorDto fetchTemperatureById(Long id);
}
