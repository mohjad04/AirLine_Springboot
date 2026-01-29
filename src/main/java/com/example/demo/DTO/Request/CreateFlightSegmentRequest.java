package com.example.demo.DTO.Request;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class CreateFlightSegmentRequest {
    private Long flightId;
    private Integer segmentNo;
    private Long fromAirportId;
    private Long toAirportId;
    private OffsetDateTime departureTime;
    private OffsetDateTime arrivalTime;
}
