package com.example.demo.Model.entities;



import com.example.demo.Model.enums.BaggageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "baggage_items")
public class BaggageItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @Enumerated(EnumType.STRING)
    private BaggageType bagType;

    private BigDecimal weightKg;
    private String tagNumber;
    private String notes;
}
