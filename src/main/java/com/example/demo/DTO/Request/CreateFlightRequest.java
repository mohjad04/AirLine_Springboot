package com.example.demo.DTO.Request;

import lombok.Data;

@Data
public class CreateFlightRequest {
    private String flightNumber;
    private Long airplaneId;
}
