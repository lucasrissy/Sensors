package com.example.sensor.service.client;

import com.example.sensor.dto.LuminosityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("luminosity")
public interface LuminosityFeignClient {

    @GetMapping(value = "api/sensor", consumes = "application/json")
    ResponseEntity<LuminosityDto> fetchLuminositySensor(@RequestParam Long id);

    @GetMapping(value = "api/getAll", consumes = "application/json")
    ResponseEntity<Page<LuminosityDto>> pageLuminosity();
}
