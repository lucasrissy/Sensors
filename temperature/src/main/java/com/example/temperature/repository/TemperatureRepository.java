package com.example.temperature.repository;

import com.example.temperature.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<Temperature,Long> {
}
