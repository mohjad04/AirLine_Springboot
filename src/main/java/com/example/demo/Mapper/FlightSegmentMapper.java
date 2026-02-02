package com.example.demo.Mapper;

import com.example.demo.DTO.Request.CreateFlightSegmentRequest;
import com.example.demo.Model.entities.FlightSegment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FlightSegmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "flight", ignore = true)
    @Mapping(target = "fromAirport", ignore = true)
    @Mapping(target = "toAirport", ignore = true)
    FlightSegment toSegment(CreateFlightSegmentRequest req);
}
