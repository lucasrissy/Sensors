package com.example.sensor.controller;

import com.example.sensor.dto.SensorDetailsDto;
import com.example.sensor.service.SensorDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor
public class SensorDetailsController {

    @Autowired
    private SensorDetailsService service;


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
    public ResponseEntity<SensorDetailsDto> fetchSensorById(@RequestParam Long id){

        System.out.println("this is id: "+ id.toString());
        return ResponseEntity.status(HttpStatus.OK).body(service.fetchRecordById(id));
    }
}
