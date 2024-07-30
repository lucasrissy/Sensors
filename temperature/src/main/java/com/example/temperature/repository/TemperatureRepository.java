package com.example.temperature.repository;

import com.example.temperature.entity.TemperatureSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<TemperatureSensor,Long> {
}
