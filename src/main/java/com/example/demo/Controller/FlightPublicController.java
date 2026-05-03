package com.example.demo.Controller;

import com.example.demo.DTO.Response.FlightListItemDto;
import com.example.demo.Service.FlightPublicService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class FlightPublicController {

    private final FlightPublicService flightPublicService;

    public FlightPublicController(FlightPublicService flightPublicService) {
        this.flightPublicService = flightPublicService;
    }

    @GetMapping
    public Page<FlightListItemDto> getFlights(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean ascending
    ) {
        return flightPublicService.getFlights(page, size, sortBy, ascending);
    }
}
