package com.example.demo.Model.entities;



import com.example.demo.Model.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name="airplane_id", nullable = false)
    private AirPlane airplane;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", columnDefinition = "flight_status")
    private FlightStatus status;

    @ManyToOne
    @JoinColumn(name="created_by_staff_id")
    private Staff createdByStaff;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;
}

