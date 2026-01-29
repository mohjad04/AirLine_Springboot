package com.example.demo.Mapper;

import com.example.demo.DTO.Request.CreateAirplaneRequest;
import com.example.demo.Model.entities.AirPlane;

public class AirPlaneMapper {
    public static AirPlane toEntity(CreateAirplaneRequest req) {
        AirPlane p = new AirPlane();
        p.setModel(req.getModel());
        p.setRegistrationNo(req.getRegistrationNo());
        p.setSeatCapacity(req.getSeatCapacity());
        return p;
    }
}
