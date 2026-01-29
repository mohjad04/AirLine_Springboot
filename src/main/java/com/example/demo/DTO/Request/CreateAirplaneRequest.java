package com.example.demo.DTO.Request;

import lombok.Data;

@Data
public class CreateAirplaneRequest {
    private String model;
    private String registrationNo;
    private Integer seatCapacity;
}
