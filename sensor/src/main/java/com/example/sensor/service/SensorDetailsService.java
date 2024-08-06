package com.example.sensor.service;

import com.example.sensor.dto.LuminosityDto;
import com.example.sensor.dto.SensorDetailsDto;
import com.example.sensor.dto.TemperatureDto;
import com.example.sensor.service.client.LuminosityFeignClient;
import com.example.sensor.service.client.TemperatureFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SensorDetailsService {

    @Autowired
    public SensorDetailsService(LuminosityFeignClient luminosity, TemperatureFeignClient temperature) {
        this.luminosity = luminosity;
        this.temperature = temperature;
    }

    @Autowired
    private LuminosityFeignClient luminosity;

    @Autowired
    private TemperatureFeignClient temperature;


    public SensorDetailsDto fetchRecordById(Long id){

        SensorDetailsDto sensorDetailsDto = new SensorDetailsDto();

        ResponseEntity<LuminosityDto> luminositySensor = luminosity.fetchLuminositySensor(id);

        sensorDetailsDto.setLuminosity(luminositySensor.getBody());

        ResponseEntity<TemperatureDto> temperatureSensor = temperature.fetchTemperatureSensor(id);

        sensorDetailsDto.setTemperature(temperatureSensor.getBody());

        return sensorDetailsDto;

    }
}
