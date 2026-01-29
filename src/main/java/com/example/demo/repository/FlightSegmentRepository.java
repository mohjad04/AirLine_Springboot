package com.example.demo.repository;

import com.example.demo.Model.entities.FlightSegment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightSegmentRepository extends JpaRepository<FlightSegment, Long> {
}
