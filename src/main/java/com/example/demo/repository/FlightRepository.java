package com.example.demo.repository;

import com.example.demo.Model.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight findById(long id);
}
