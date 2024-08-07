package com.example.sensor.controller;

import com.example.sensor.dto.SensorDetailsDto;
import com.example.sensor.service.SensorDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor
public class SensorDetailsController {

    @Autowired
    private SensorDetailsService service;


    @Operation(
            summary = "Retrieve details of a singles sensor",
            description = "REST API endpoint to retrieve sensor details by ID"
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
        return ResponseEntity.status(HttpStatus.OK).body(service.fetchRecordById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<SensorDetailsDto>> pageAllSensors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id, asc") String[] sort){

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort sortBy = Sort.by(direction,sort[0]);

        Pageable pageable = PageRequest.of(page,size,sortBy);

        return ResponseEntity.status(HttpStatus.OK).body(service.pageAllDataSensor(pageable));
    }

    @GetMapping("/export")
    public void exportData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id, asc") String[] sort){

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort sortBy = Sort.by(direction,sort[0]);

        Pageable pageable = PageRequest.of(page,size,sortBy);

        Page<SensorDetailsDto> dtoPage = service.pageAllDataSensor(pageable);

        exportDataToFile(dtoPage,"sensor_data.txt");

    }

    public void exportDataToFile(Page<SensorDetailsDto> sensorDetailsDtos, String fileName){

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){

            writer.write("Luminosity, Temperature");

            writer.newLine();

            for (SensorDetailsDto detailsDto : sensorDetailsDtos.getContent()){

                String line = String.format("%s,%s",
                        detailsDto.getLuminosity().toString(),
                        detailsDto.getTemperature().toString());

                writer.write(line);
                writer.newLine();
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
