package com.example.luminosity.service;

import com.example.luminosity.dto.LuminosityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceLuminosityInterface {

    LuminosityDto fetchTemperatureById(Long id);

    Page<LuminosityDto> pageAllSensors(Pageable pageable);

    LuminosityDto registry(LuminosityDto sensorDto);

    boolean updateSensor (LuminosityDto dto,Long id);

    boolean deleteSensor(Long id);
}
