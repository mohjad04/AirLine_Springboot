package com.example.demo.Mapper;

import com.example.demo.DTO.Request.CreateFlightSegmentRequest;
import com.example.demo.Model.entities.AirPort;
import com.example.demo.Model.entities.Flight;
import com.example.demo.Model.entities.FlightSegment;

public class FlightSegmentMapper {

    public static FlightSegment toEntity(CreateFlightSegmentRequest req) {
        FlightSegment s = new FlightSegment();

        // Flight relation (by id)
        Flight f = new Flight();
        f.setId(req.getFlightId());
        s.setFlight(f);

        // Airports relations (by id)
        AirPort from = new AirPort();
        from.setId(req.getFromAirportId());
        s.setFromAirport(from);

        AirPort to = new AirPort();
        to.setId(req.getToAirportId());
        s.setToAirport(to);

        s.setSegmentNo(req.getSegmentNo());
        s.setDepartureTime(req.getDepartureTime());
        s.setArrivalTime(req.getArrivalTime());

        return s;
    }
}
