package com.example.demo.repository;

import com.example.demo.Model.entities.AirPlane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirPlaneRepository extends JpaRepository<AirPlane, Long> {
    AirPlane findById(long id);
    AirPlane findByRegistrationNo(String registrationNo);
}
