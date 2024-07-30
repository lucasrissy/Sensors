package com.example.temperature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureSensorDto {

    private Long id;

    private Integer temperature;

    private LocalDate timestamp;
}
