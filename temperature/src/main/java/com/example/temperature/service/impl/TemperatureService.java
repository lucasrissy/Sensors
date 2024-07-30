package com.example.temperature.service.impl;

import com.example.temperature.dto.TemperatureSensorDto;
import com.example.temperature.entity.TemperatureSensor;
import com.example.temperature.exceptions.ResourceNotFound;
import com.example.temperature.mapper.TemperatureMapper;
import com.example.temperature.repository.TemperatureRepository;
import com.example.temperature.service.TemperatureServiceInterface;
import lombok.AllArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.sql.SQLException;

@Service
@AllArgsConstructor
public class TemperatureService implements TemperatureServiceInterface {


    private TemperatureRepository repository;

    @Override
    public TemperatureSensorDto fetchTemperatureById(Long id) {

        return TemperatureMapper.mapToDto( new TemperatureSensorDto(),repository.findById(id)
                .orElseThrow( () -> new ResourceNotFound("Sensor not found","id",id.toString())));
    }

    @Override
    public Page<TemperatureSensorDto> pageAllSensors(Pageable pageable) {

        return  repository.findAll(pageable)
                .map(element -> TemperatureMapper
                        .mapToDto(new TemperatureSensorDto(),element));
    }

    @Override
    public TemperatureSensorDto registry(TemperatureSensorDto sensorDto) {

        if (sensorDto == null){
            throw new NullPointerException("this sensor is null: "+sensorDto);
        }
        return TemperatureMapper.mapToDto(new TemperatureSensorDto(),repository.save(TemperatureMapper.mapToEntity(sensorDto,new TemperatureSensor()))) ;
    }

    @Override
    public boolean updateSensor(TemperatureSensorDto dto, Long id) {

        boolean isUpdate = false;
        if (repository.existsById(id)){
            try {
                TemperatureSensor temperatureSensor = repository.getReferenceById(id);
                temperatureSensor.setTemperature(dto.getTemperature());
                temperatureSensor.setTimestamp(dto.getTimestamp());
                repository.save(temperatureSensor);
                isUpdate = true;

            }catch (Exception e){
                throw new ResourceNotFound("Resource not found","ds","sds");
            }
        }
       return isUpdate;
    }

    @Override
    public boolean deleteSensor(Long id) {

        boolean isDeleted = false;

        if (repository.existsById(id)){
            isDeleted = true;
            repository.deleteById(id);
        }

        return isDeleted;
    }

}
