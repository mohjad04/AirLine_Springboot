package com.example.demo.DTO.Response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateStaffResponse {
    private Long staffUserId;
    private String message;
}
