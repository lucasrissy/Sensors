package com.example.temperature.controller;

import com.example.temperature.dto.ResponseDto;
import com.example.temperature.dto.ResponseErrorDto;
import com.example.temperature.dto.TemperatureSensorDto;
import com.example.temperature.service.TemperatureServiceInterface;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "CRUD REST APIs for temperature in Sensor",
        description = "CRUD REST APIs in Temperature Sensor to CREATE, UPDATE, FETCH AND DELETE data details"
)
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class TemperatureSensorController {

    private TemperatureServiceInterface service;

    @Operation(
            summary = "Retrieve details of a singles temperature sensor",
            description = "REST API endpoint to retrieve temperature sensor details by ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ) ,
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @GetMapping("/sensor")
    public ResponseEntity<TemperatureSensorDto> fetchSensorById(@RequestParam @PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(service.fetchTemperatureById(id));
    }


    @Operation(
            summary = "Retrieve all details temperature sensors",
            description = "REST API endpoint to retrieve all temperature sensor details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ) ,
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
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

    @Operation(
            summary = "Create a new temperature sensor record",
            description = "REST API endpoint to create a new temperature sensor record with the provided details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ) ,
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @PostMapping("/registry")
    public ResponseEntity<TemperatureSensorDto> registry(@RequestBody TemperatureSensorDto dto){


        return ResponseEntity.status(HttpStatus.CREATED).body(service.registry(dto));
    }

    @Operation(
            summary = "Update a temperature sensor record",
            description = "REST API endpoint to update a temperature sensor record with the provided details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
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

    @Operation(
            summary = "Delete temperature record REST API",
            description = "REST API to delete temperature details based on id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
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
