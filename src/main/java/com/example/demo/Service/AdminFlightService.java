package com.example.demo.Service;

import com.example.demo.DTO.Request.CreateFlightRequest;
import com.example.demo.DTO.Request.CreateFlightSegmentRequest;
import com.example.demo.Mapper.AdminFlightMapper;
import com.example.demo.Mapper.FlightSegmentMapper;
import com.example.demo.Model.entities.*;
import com.example.demo.Util.ApiException;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminFlightService {

    private final AdminAuthHelper adminAuthHelper;
    private final AirPlaneRepository airPlaneRepository;
    private final FlightRepository flightRepository;
    private final StaffRepository staffRepository;
    private final AdminFlightMapper adminFlightMapper;

    private final FlightSegmentRepository flightSegmentRepository;
    private final AirportRepository airportRepository;
    private final FlightSegmentMapper flightSegmentMapper;

    public AdminFlightService(
            AdminAuthHelper adminAuthHelper,
            AirPlaneRepository airPlaneRepository,
            FlightRepository flightRepository,
            StaffRepository staffRepository,
            FlightSegmentRepository flightSegmentRepository,
            AirportRepository airportRepository,
            AdminFlightMapper adminFlightMapper,
            FlightSegmentMapper flightSegmentMapper
    ) {
        this.adminAuthHelper = adminAuthHelper;
        this.airPlaneRepository = airPlaneRepository;
        this.flightRepository = flightRepository;
        this.staffRepository = staffRepository;
        this.flightSegmentRepository = flightSegmentRepository;
        this.airportRepository = airportRepository;
        this.adminFlightMapper = adminFlightMapper;
        this.flightSegmentMapper = flightSegmentMapper;
    }

    @Transactional
    public Long createFlight(CreateFlightRequest req) {

        Long adminUserId = adminAuthHelper.requireAdmin();

        if (req.getFlightNumber() == null || req.getFlightNumber().isBlank())
            throw new ApiException("flightNumber required");
        if (req.getAirplaneId() == null)
            throw new ApiException("airplaneId required");

        AirPlane airplane = airPlaneRepository.findById(req.getAirplaneId())
                .orElseThrow(() -> new ApiException("airplane not found"));

        Staff staff = staffRepository.findById(adminUserId)
                .orElseThrow(() -> new ApiException("staff not found"));

        Flight f = adminFlightMapper.toFlight(req);
        f.setAirplane(airplane);
        f.setCreatedByStaff(staff);

        return flightRepository.save(f).getId();
    }

    @Transactional
    public Long createFlightSegment(CreateFlightSegmentRequest req) {

        adminAuthHelper.requireAdmin(); // ✅ just check admin

        if (req.getFlightId() == null) throw new ApiException("flightId required");
        if (req.getSegmentNo() == null || req.getSegmentNo() <= 0) throw new ApiException("segmentNo required");
        if (req.getFromAirportId() == null || req.getToAirportId() == null) throw new ApiException("from/to required");
        if (req.getFromAirportId().equals(req.getToAirportId())) throw new ApiException("from and to must differ");
        if (req.getDepartureTime() == null || req.getArrivalTime() == null) throw new ApiException("times required");
        if (!req.getArrivalTime().isAfter(req.getDepartureTime())) throw new ApiException("arrival must be after departure");

        Flight flight = flightRepository.findById(req.getFlightId())
                .orElseThrow(() -> new ApiException("flight not found"));

        AirPort from = airportRepository.findById(req.getFromAirportId())
                .orElseThrow(() -> new ApiException("from airport not found"));

        AirPort to = airportRepository.findById(req.getToAirportId())
                .orElseThrow(() -> new ApiException("to airport not found"));

        FlightSegment seg = flightSegmentMapper.toSegment(req);
        seg.setFlight(flight);
        seg.setFromAirport(from);
        seg.setToAirport(to);

        return flightSegmentRepository.save(seg).getId();
    }
}
