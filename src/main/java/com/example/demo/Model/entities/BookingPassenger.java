package com.example.demo.Model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(BookingPassengerKey.class)
@Table(name = "booking_passengers")
public class BookingPassenger {

    @Id
    @Column(name = "booking_id")
    private Long bookingId;

    @Id
    @Column(name = "passenger_id")
    private Long passengerId;

    @Column(name = "seat_id", nullable = false)
    private Long seatId;
}
