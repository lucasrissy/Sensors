package com.example.temperature.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "tb_temperature")
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer temperature;

    private LocalDate timestamp;

    @Override
    public String toString() {
        return "TemperatureSensor{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", timestamp=" + timestamp +
                '}';
    }
}
