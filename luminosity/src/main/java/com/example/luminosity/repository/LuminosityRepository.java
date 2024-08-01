package com.example.luminosity.repository;

import com.example.luminosity.entity.Luminosity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LuminosityRepository extends JpaRepository<Luminosity,Long> {
}
