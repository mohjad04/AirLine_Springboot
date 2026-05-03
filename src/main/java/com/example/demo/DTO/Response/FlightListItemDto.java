package com.example.demo.DTO.Response;

import com.example.demo.Model.enums.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightListItemDto {
    private Long id;
    private String flightNumber;
    private FlightStatus status;
    private OffsetDateTime createdAt;

    private Long airplaneId;
    private String airplaneModel;
    private String registrationNo;
}
