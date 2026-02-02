package com.example.demo.Service;

import com.example.demo.Model.entities.Staff;
import com.example.demo.Model.enums.StaffRole;
import com.example.demo.repository.StaffRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthHelper {

    private final AuthService authService;
    private final StaffRepository staffRepository;

    public AdminAuthHelper(AuthService authService, StaffRepository staffRepository) {
        this.authService = authService;
        this.staffRepository = staffRepository;
    }

    public Long requireAdmin(String authorizationHeader) {
        Long userId = authService.requireUserId(authorizationHeader);

        Staff staff = staffRepository.findByUserId(userId);
        if (staff == null || staff.getRole() != StaffRole.ADMIN) {
            throw new RuntimeException("Forbidden: ADMIN only");
        }
        return userId; // admin user_id (staff PK)
    }
}
