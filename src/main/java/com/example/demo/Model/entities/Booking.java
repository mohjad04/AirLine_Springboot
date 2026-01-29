package com.example.demo.Model.entities;


import com.example.demo.Model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingRef;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="flight_id", nullable = false)
    private Flight flight;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;


    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;
}
