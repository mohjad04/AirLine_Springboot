package com.example.demo.Controller;

import com.example.demo.DTO.Request.CreateStaffRequest;
import com.example.demo.DTO.Response.CreateStaffResponse;
import com.example.demo.Service.AdminStaffService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/staff")
public class AdminStaffController {

    private final AdminStaffService adminStaffService;

    public AdminStaffController(AdminStaffService adminStaffService) {
        this.adminStaffService = adminStaffService;
    }

    @PostMapping
    public CreateStaffResponse createStaff(
            @RequestHeader("Authorization") String authorization,
            @RequestBody CreateStaffRequest req
    ) {
        Long id = adminStaffService.createStaff(authorization, req);
        return new CreateStaffResponse(id, "Staff created");
    }
}
