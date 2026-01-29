package com.example.demo.repository;


import com.example.demo.Model.entities.AirPort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<AirPort, Long> {
    AirPort findById(long id);
}
