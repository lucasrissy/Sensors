package com.example.temperature.controller;

import com.example.temperature.dto.TemperatureSensorDto;
import com.example.temperature.service.TemperatureServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class TemperatureSensorController {

    private TemperatureServiceInterface service;

    @GetMapping("/sensor")
    public ResponseEntity<TemperatureSensorDto> fetchSensorById(@RequestParam @PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(service.fetchTemperatureById(id));
    }
}
