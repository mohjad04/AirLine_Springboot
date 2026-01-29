package com.example.demo.Controller;

import com.example.demo.DTO.Request.CreateFlightSegmentRequest;
import com.example.demo.DTO.Request.CreateStaffRequest;
import com.example.demo.Service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/staff")
    public Map<String, Object> createStaff(
            @RequestHeader("Authorization") String authorization,
            @RequestBody CreateStaffRequest req
    ) {
        Long newUserId = adminService.createStaff(authorization, req);
        return Map.of("staffUserId", newUserId, "message", "Staff created");
    }

    @PostMapping("/flights")
    public java.util.Map<String, Object> createFlight(
            @RequestHeader("Authorization") String auth,
            @RequestBody com.example.demo.DTO.Request.CreateFlightRequest req
    ) {
        Long id = adminService.createFlight(auth, req);
        return java.util.Map.of("flightId", id);
    }

    @PostMapping("/flight-segments")
    public java.util.Map<String, Object> createFlightSegment(
            @RequestHeader("Authorization") String auth,
            @RequestBody CreateFlightSegmentRequest req
    ) {
        Long id = adminService.createFlightSegment(auth, req);
        return java.util.Map.of("segmentId", id);
    }

}
