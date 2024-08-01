package com.example.luminosity.service.impl;

import com.example.luminosity.dto.LuminosityDto;
import com.example.luminosity.entity.Luminosity;
import com.example.luminosity.exception.ResourceNotFound;
import com.example.luminosity.mapper.LuminosityMapper;
import com.example.luminosity.service.ServiceLuminosityInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.luminosity.repository.LuminosityRepository;

@Service
@AllArgsConstructor
public class LuminosityServiceImpl implements ServiceLuminosityInterface {

    private LuminosityRepository repository;

    @Override
    public LuminosityDto fetchTemperatureById(Long id) {

        Luminosity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Resource not found","id",id.toString()));

        return LuminosityMapper.mapToDto(entity, new LuminosityDto());
    }

    @Override
    public Page<LuminosityDto> pageAllSensors(Pageable pageable) {

        return repository.findAll(pageable)
                .map(e -> LuminosityMapper.mapToDto(e,new LuminosityDto()));
    }

    @Override
    public LuminosityDto registry(LuminosityDto sensorDto) {

        if (sensorDto == null){
            throw new NullPointerException("the sensor is null");
        }
        return LuminosityMapper.mapToDto(repository
                .save(LuminosityMapper
                        .mapToEntity(new Luminosity(),sensorDto)),new LuminosityDto());
    }

    @Override
    public boolean updateSensor(LuminosityDto dto, Long id) {

        boolean isUpdate = false;

        if (repository.existsById(id)){

            repository.save(LuminosityMapper.mapToEntity(new Luminosity(),dto));

            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public boolean deleteSensor(Long id) {

        boolean isUpdate = false;

        if (repository.existsById(id)){

            repository.deleteById(id);

            isUpdate = true;
        }
        return isUpdate;
    }
}
