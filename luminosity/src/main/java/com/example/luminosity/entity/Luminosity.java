package com.example.luminosity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_luminosity")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Luminosity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer luminosity;

    private LocalDate timestamp;
}
