package com.example.temperature.controller;

import com.example.temperature.dto.ResponseDto;
import com.example.temperature.dto.ResponseErrorDto;
import com.example.temperature.dto.TemperatureSensorDto;
import com.example.temperature.service.TemperatureServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class TemperatureSensorController {

    private TemperatureServiceInterface service;

    @GetMapping("/sensor")
    public ResponseEntity<TemperatureSensorDto> fetchSensorById(@RequestParam @PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(service.fetchTemperatureById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<TemperatureSensorDto>> pageAllSensors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id, asc") String[] sort){

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort sortBy = Sort.by(direction,sort[0]);

        Pageable pageable = PageRequest.of(page,size,sortBy);

        return ResponseEntity.status(HttpStatus.OK).body(service.pageAllSensors(pageable));
    }

    @PostMapping("/registry")
    public ResponseEntity<TemperatureSensorDto> registry(@RequestBody TemperatureSensorDto dto){


        return ResponseEntity.status(HttpStatus.CREATED).body(service.registry(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> update(@RequestBody TemperatureSensorDto dto, @PathVariable Long id){

        if(service.updateSensor(dto,id)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(HttpStatus.OK,"Update was realized!"));
        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(HttpStatus.EXPECTATION_FAILED,"update was not realized!"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id){

        boolean isDeleted = service.deleteSensor(id);

        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(HttpStatus.OK,"Delete was realized!"));
        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(HttpStatus.EXPECTATION_FAILED,"Delete was not realized!"));
        }

    }
}
