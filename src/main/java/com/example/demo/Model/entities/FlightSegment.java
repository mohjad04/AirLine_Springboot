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
@Table(name = "flight_segments")
public class FlightSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="flight_id", nullable = false)
    private Flight flight;
    private Integer segmentNo;

    @ManyToOne
    @JoinColumn(name="from_airport_id", nullable = false)
    private AirPort fromAirport;

    @ManyToOne
    @JoinColumn(name="to_airport_id", nullable = false)
    private AirPort toAirport;

    private OffsetDateTime departureTime;
    private OffsetDateTime arrivalTime;
}
