package com.example.luminosity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LuminosityDto {

    private Long UID;

    private Integer luminosity;

    private LocalDate timestamp;
}
