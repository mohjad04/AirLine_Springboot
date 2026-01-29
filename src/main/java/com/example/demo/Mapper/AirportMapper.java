package com.example.demo.Mapper;

import com.example.demo.DTO.Request.CreateAirportRequest;
import com.example.demo.Model.entities.AirPort;

public class AirportMapper {
    public static AirPort toEntity(CreateAirportRequest req) {
        AirPort a = new AirPort();
        a.setName(req.getName());
        a.setCity(req.getCity());
        a.setCountry(req.getCountry());
        return a;
    }
}
