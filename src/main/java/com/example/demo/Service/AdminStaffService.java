package com.example.demo.Service;

import com.example.demo.DTO.Request.CreateStaffRequest;
import com.example.demo.Mapper.AdminStaffMapper;
import com.example.demo.Model.entities.Staff;
import com.example.demo.Model.entities.User;
import com.example.demo.repository.StaffRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminStaffService {

    private final AdminAuthHelper adminAuthHelper;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;
    private final AdminStaffMapper adminStaffMapper;

    public AdminStaffService(AdminAuthHelper adminAuthHelper,
                             UserRepository userRepository,
                             StaffRepository staffRepository,
                             AdminStaffMapper adminStaffMapper) {
        this.adminAuthHelper = adminAuthHelper;
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.adminStaffMapper = adminStaffMapper;
    }

    @Transactional
    public Long createStaff( CreateStaffRequest req) {

        // 1) authorization
        adminAuthHelper.requireAdmin();

        // 2) validations
        if (req.getEmail() == null || req.getPassword() == null)
            throw new RuntimeException("email and password required");
        if (req.getRole() == null)
            throw new RuntimeException("role is required");
        if (req.getEmployeeCode() == null)
            throw new RuntimeException("employeeCode is required");

        userRepository.findByEmail(req.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("email already exists");
                });

        // 4) create user
        User user = adminStaffMapper.toUser(req);
        User savedUser = userRepository.save(user);

        // 5) create staff (PK = user_id)
        Staff staff = adminStaffMapper.toStaff(req);
        staff.setUserId(savedUser.getId());
        staffRepository.save(staff);

        return savedUser.getId();
    }
}
