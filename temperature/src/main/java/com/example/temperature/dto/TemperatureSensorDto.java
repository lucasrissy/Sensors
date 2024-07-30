package com.example.temperature.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TemperatureSensorDto {

    private Long id;

    private Integer temperature;

    private LocalDate timestamp;
}
