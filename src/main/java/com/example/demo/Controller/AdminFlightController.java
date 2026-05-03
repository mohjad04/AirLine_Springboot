package com.example.demo.Controller;

import com.example.demo.DTO.Request.CreateFlightRequest;
import com.example.demo.DTO.Request.CreateFlightSegmentRequest;
import com.example.demo.DTO.Response.CreateFlightResponse;
import com.example.demo.DTO.Response.CreateFlightSegmentResponse;
import com.example.demo.Service.AdminFlightService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminFlightController {

    private final AdminFlightService adminFlightService;

    public AdminFlightController(AdminFlightService adminFlightService) {
        this.adminFlightService = adminFlightService;
    }

    @PostMapping("/flights")
    public CreateFlightResponse createFlight(
            @RequestBody CreateFlightRequest req
    ) {
        Long id = adminFlightService.createFlight(req);
        return new CreateFlightResponse(id);
    }


    @PostMapping("/flight-segments")
    public CreateFlightSegmentResponse createFlightSegment(
            @RequestBody CreateFlightSegmentRequest req
    ) {
        Long id = adminFlightService.createFlightSegment( req);
        return new CreateFlightSegmentResponse(id);
    }
}
