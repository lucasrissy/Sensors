package com.example.sensor.service;

import com.example.sensor.dto.LuminosityDto;
import com.example.sensor.dto.SensorDetailsDto;
import com.example.sensor.dto.TemperatureDto;
import com.example.sensor.service.client.LuminosityFeignClient;
import com.example.sensor.service.client.TemperatureFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public Page<SensorDetailsDto> pageAllDataSensor(Pageable pageable) {

        Page<LuminosityDto> luminosityDtoPage = luminosity.pageLuminosity().getBody();

        Page<TemperatureDto> temperatureDtoPage = temperature.pageTemperature().getBody();

        List<SensorDetailsDto> combinedList = new ArrayList<>();

        int i;
        for (i = 0;i <Math.max(luminosityDtoPage.getSize(), temperatureDtoPage.getSize()); i++){

            combinedList.add(new SensorDetailsDto(luminosityDtoPage.getContent().get(i),temperatureDtoPage.getContent().get(i)));
        }

        return new PageImpl<>(combinedList);
    }
}
