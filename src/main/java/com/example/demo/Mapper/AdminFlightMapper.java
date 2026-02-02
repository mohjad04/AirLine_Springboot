package com.example.demo.Mapper;

import com.example.demo.DTO.Request.CreateFlightRequest;
import com.example.demo.Model.entities.Flight;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdminFlightMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "airplane", ignore = true)
    @Mapping(target = "createdByStaff", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", constant = "SCHEDULED")
    Flight toFlight(CreateFlightRequest req);
}
