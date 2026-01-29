package com.example.demo.DTO.Request;


import com.example.demo.Model.enums.StaffRole;
import lombok.Data;

@Data
public class CreateStaffRequest {
    private String fullName;
    private String email;
    private String password;
    private String phone;

    private StaffRole role;
    private String employeeCode;
    private String jobTitle;
    private String licenseNo;
}
