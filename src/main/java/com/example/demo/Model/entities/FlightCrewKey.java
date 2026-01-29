package com.example.demo.Model.entities;

import lombok.Data;
import java.io.Serializable;

@Data
public class FlightCrewKey implements Serializable {
    private Long flightId;
    private Long staffId;
}
