package com.example.temperature.service;

import com.example.temperature.dto.TemperatureSensorDto;
import com.example.temperature.entity.Temperature;
import com.example.temperature.exceptions.ResourceNotFound;
import com.example.temperature.mapper.TemperatureMapper;
import com.example.temperature.repository.TemperatureRepository;
import com.example.temperature.service.impl.TemperatureService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private TemperatureRepository repository;

    @InjectMocks
    private TemperatureService service;

    private Long id;

    private Temperature entity;

    private TemperatureSensorDto dto;

    private PageImpl<Temperature> page;

    @BeforeEach
    public  void setup(){

        id = 1L;

        entity = new Temperature();
        entity.setUID(1L);
        entity.setTimestamp(LocalDate.now());
        entity.setTemperature(30);

        page = new PageImpl<>(List.of(entity));

        dto = TemperatureMapper.mapToDto(new TemperatureSensorDto(),entity);
    }

    @Test
    public void fetchTemperatureByIdShouldReturnTemperatureDtoWhenIdExisting(){

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));

        TemperatureSensorDto dtoTest = service.fetchTemperatureById(1L);

        Assertions.assertEquals(dtoTest.getTemperature(), entity.getTemperature());

        Assertions.assertEquals(dtoTest.getTimestamp(), entity.getTimestamp());

        Assertions.assertNotNull(dtoTest);
    }

    @Test
    public void fetchTemperatureByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNoExisting(){

        Assertions.assertThrows(ResourceNotFound.class,()->{
            service.fetchTemperatureById(2L);
        });
    }

    @Test
    public void pageAllTemperatureRecordsShouldReturnWhenPageableIsValid(){

        Pageable pageable = PageRequest.of(0,5);

        Mockito.when(repository.findAll(pageable)).thenReturn(page);

        Page<TemperatureSensorDto> dtoPage = service.pageAllSensors(pageable);

        Assertions.assertNotNull(dtoPage);
        Assertions.assertEquals(dtoPage.getTotalPages(),1);

    }

    @Test
    public void registryNewTemperatureRecordShouldReturnTemperatureDtoRecordWhenDataIsValid(){

        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(entity);

        TemperatureSensorDto temperatureSensorDto = service.registry(dto);

        Assertions.assertNotNull(temperatureSensorDto);
        Assertions.assertEquals(temperatureSensorDto.getTemperature(),entity.getTemperature());
        Assertions.assertEquals(temperatureSensorDto.getTimestamp(),entity.getTimestamp());

    }

    @Test
    public void registryNewTemperatureRecordShouldReturnNullPointerExceptionWhenDataIsNull(){

        Assertions.assertThrows(NullPointerException.class, () ->{
            service.registry(null);
        });
    }

    @Test
    public void updateTemperatureRecordShouldReturnTrueWhenDataIsValid(){

        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(entity);
        Mockito.when(repository.existsById(ArgumentMatchers.any())).thenReturn(true);
        Mockito.when(repository.getReferenceById(ArgumentMatchers.any())).thenReturn(entity);

        boolean isUpdate = service.updateSensor(dto,id);

        Assertions.assertTrue(isUpdate);

        Mockito.verify(repository,Mockito.times(1)).existsById(id);
        Mockito.verify(repository,Mockito.times(1)).getReferenceById(id);
        Mockito.verify(repository,Mockito.times(1)).save(entity);
    }

    @Test
    public void updateTemperatureRecordShouldReturnFalseWhenIdDoesNotExist(){

        Mockito.when(repository.existsById(ArgumentMatchers.any())).thenReturn(false);

        boolean result = service.updateSensor(dto,2L);

        Assertions.assertFalse(result);

        Mockito.verify(repository, Mockito.times(1)).existsById(2L);
    }

    @Test
    public void deleteTemperatureRecordShouldReturnTrueWhenIdExiting(){

        Mockito.when(repository.existsById(ArgumentMatchers.any())).thenReturn(true);

        boolean result = service.deleteSensor(id);

        Assertions.assertTrue(result);
    }

    @Test
    public void deleteTemperatureRecordShouldReturnFalseWhenIdDoesNotValid(){

        Mockito.when(repository.existsById(ArgumentMatchers.any())).thenReturn(false);

        boolean result = service.deleteSensor(id);

        Assertions.assertFalse(result);
    }
}
