package com.example.demo.Mapper;

import com.example.demo.DTO.Request.CreateFlightRequest;
import com.example.demo.Model.entities.AirPlane;
import com.example.demo.Model.entities.Flight;
import com.example.demo.Model.entities.Staff;
import com.example.demo.Model.enums.FlightStatus;

public class FlightMapper {

    public static Flight toEntity(CreateFlightRequest req, Long createdByStaffId) {
        Flight f = new Flight();

        f.setFlightNumber(req.getFlightNumber());

        AirPlane airplane = new AirPlane();
        airplane.setId(req.getAirplaneId());
        f.setAirplane(airplane);

        f.setStatus(FlightStatus.SCHEDULED);

        if (createdByStaffId != null) {
            Staff staff = new Staff();
            staff.setUserId(createdByStaffId);
            f.setCreatedByStaff(staff);
        }

        return f;
    }
}
