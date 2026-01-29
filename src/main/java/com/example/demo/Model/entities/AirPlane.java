package com.example.demo.Model.entities;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airplanes")
public class AirPlane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String registrationNo;
    private Integer seatCapacity;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;
}

