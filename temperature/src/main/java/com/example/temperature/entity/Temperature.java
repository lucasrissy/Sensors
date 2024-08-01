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
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    private Integer temperature;

    private LocalDate timestamp;

    @Override
    public String toString() {
        return "TemperatureSensor{" +
                "id=" + UID +
                ", temperature=" + temperature +
                ", timestamp=" + timestamp +
                '}';
    }
}
