package com.example.demo.DTO.Request;


import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateCustomerRequest {
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private LocalDate dateOfBirth;
}