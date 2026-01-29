package com.example.demo.Model.entities;

import com.example.demo.Model.enums.StaffRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(FlightCrewKey.class)
@Table(name = "flight_crew")
public class FlightCrew {

    @Id
    @Column(name = "flight_id")
    private Long flightId;

    @Id
    @Column(name = "staff_id")
    private Long staffId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "staff_role")
    private StaffRole role;
}
