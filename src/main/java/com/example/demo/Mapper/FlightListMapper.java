package com.example.demo.Mapper;

import com.example.demo.DTO.Response.FlightListItemDto;
import com.example.demo.Model.entities.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlightListMapper {

    @Mapping(target = "airplaneId", source = "airplane.id")
    @Mapping(target = "airplaneModel", source = "airplane.model")
    @Mapping(target = "registrationNo", source = "airplane.registrationNo")
    FlightListItemDto toDto(Flight flight);
}
