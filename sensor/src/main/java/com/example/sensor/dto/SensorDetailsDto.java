package com.example.sensor.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Temperature Sensor",
        description = "Schema to hold Temperature Sensor information"
)
public class SensorDetailsDto {

    @Schema(
            description = "Luminosity details of the Sensor"
    )
    private LuminosityDto luminosity;

    @Schema(
            description = "Temperature details of the Sensor"
    )
    private TemperatureDto temperature;

    public LuminosityDto getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(LuminosityDto luminosity) {
        this.luminosity = luminosity;
    }

    public TemperatureDto getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureDto temperature) {
        this.temperature = temperature;
    }
}
