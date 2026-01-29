package com.example.demo.Model.entities;


import com.example.demo.Model.enums.TicketType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airplane_id", nullable = false)
    private AirPlane airplane;
    private String seatCode;
    @Enumerated(EnumType.STRING)
    private TicketType cabinClass;
    private Boolean isActive = true;
}
