package com.example.demo.repository;

import com.example.demo.Model.entities.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("""
        select f from Flight f
        join fetch f.airplane a
    """)
    Page<Flight> findAllWithAirplane(Pageable pageable);
}
