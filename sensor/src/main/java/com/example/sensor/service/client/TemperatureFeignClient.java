package com.example.sensor.service.client;

import com.example.sensor.dto.LuminosityDto;
import com.example.sensor.dto.TemperatureDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("temperature")
public interface TemperatureFeignClient {

    @GetMapping(value = "api/sensor", consumes = "application/json")
    ResponseEntity<TemperatureDto> fetchTemperatureSensor(@RequestParam Long id);
}
