package com.example.sensor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LuminosityDto {

    @NotEmpty(message = "UID data cannot be a null or empty")
    @Schema(
            description = "The UID reading from the sensor", example = "1"
    )
    private Long UID;

    @NotEmpty(message = "Luminosity data cannot be a null or empty")
    @Schema(
            description = "The luminosity reading from the sensor", example = "23"
    )
    private Integer luminosity;

    @NotEmpty(message = "Time stamp data cannot be a null or empty")
    @Schema(
            description = "The date reading from the sensor", example = "2023-07-27"
    )
    private LocalDate timestamp;
}
