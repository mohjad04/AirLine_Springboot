package com.example.demo.Service;

import com.example.demo.Model.entities.Staff;
import com.example.demo.Model.entities.User;
import com.example.demo.Model.enums.StaffRole;
import com.example.demo.Util.ApiException;
import com.example.demo.repository.StaffRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthHelper {

    private final UserRepository userRepository;
    private final StaffRepository staffRepository;

    public AdminAuthHelper(UserRepository userRepository, StaffRepository staffRepository) {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
    }

    public Long requireAdmin() {
        // 1) Get email from Spring Security (set by JwtAuthFilter)
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 2) Get user from DB
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException("Unauthorized"));

        // 3) Check staff role
        Staff staff = staffRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ApiException("Forbidden: ADMIN only"));

        if (staff.getRole() != StaffRole.ADMIN) {
            throw new ApiException("Forbidden: ADMIN only");
        }

        return user.getId();
    }
}
