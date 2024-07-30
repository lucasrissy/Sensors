package com.example.temperature.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Temperature Sensor",
        description = "Schema to hold Temperature Sensor information"
)
public class TemperatureSensorDto {

    @NotEmpty(message = "Temperature data cannot be a null or empty")
    @Schema(
            description = "The temperature reading from the sensor", example = "23"
    )
    private Integer temperature;

    @NotEmpty(message = "Time stamp data cannot be a null or empty")
    @Schema(
            description = "The date reading from the sensor", example = "2023-07-27"
    )
    private LocalDate timestamp;
}
