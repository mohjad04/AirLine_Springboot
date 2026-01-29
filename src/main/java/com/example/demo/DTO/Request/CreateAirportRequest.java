package com.example.demo.DTO.Request;


import lombok.Data;

@Data
public class CreateAirportRequest {
    private String name;
    private String city;
    private String country;
}
