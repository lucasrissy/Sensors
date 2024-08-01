package com.example.luminosity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_name")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Luminosity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    private Integer luminosity;

    private LocalDate timestamp;
}
