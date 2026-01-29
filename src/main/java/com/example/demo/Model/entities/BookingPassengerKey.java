package com.example.demo.Model.entities;


import lombok.Data;
import java.io.Serializable;

@Data
public class BookingPassengerKey implements Serializable {
    private Long bookingId;
    private Long passengerId;
}
