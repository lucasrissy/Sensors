package com.example.luminosity.controller;


import com.example.luminosity.dto.LuminosityDto;
import com.example.luminosity.dto.ResponseDto;
import com.example.luminosity.service.ServiceLuminosityInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LuminosityController {

    private ServiceLuminosityInterface service;

    @GetMapping("/sensor")
    public ResponseEntity<LuminosityDto> fetchSensorById(@RequestParam @PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(service.fetchTemperatureById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<LuminosityDto>> pageAllSensors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id, asc") String[] sort){

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort sortBy = Sort.by(direction,sort[0]);

        Pageable pageable = PageRequest.of(page,size,sortBy);

        return ResponseEntity.status(HttpStatus.OK).body(service.pageAllSensors(pageable));
    }

    @PostMapping("/registry")
    public ResponseEntity<LuminosityDto> registry(@RequestBody LuminosityDto dto){


        return ResponseEntity.status(HttpStatus.CREATED).body(service.registry(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> update(@RequestBody LuminosityDto dto, @PathVariable Long id){

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
