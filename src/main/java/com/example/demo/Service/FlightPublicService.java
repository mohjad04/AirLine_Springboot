package com.example.demo.Service;

import com.example.demo.DTO.Response.FlightListItemDto;
import com.example.demo.Mapper.FlightListMapper;
import com.example.demo.Model.entities.Flight;
import com.example.demo.repository.FlightRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class FlightPublicService {

    private final FlightRepository flightRepository;
    private final FlightListMapper flightListMapper;

    public FlightPublicService(FlightRepository flightRepository, FlightListMapper flightListMapper) {
        this.flightRepository = flightRepository;
        this.flightListMapper = flightListMapper;
    }

    public Page<FlightListItemDto> getFlights(int page, int size, String sortBy, boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Flight> flights = flightRepository.findAllWithAirplane(pageable);

        return flights.map(flightListMapper::toDto);
    }
}
