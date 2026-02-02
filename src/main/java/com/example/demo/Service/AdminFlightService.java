package com.example.demo.Service;

import com.example.demo.DTO.Request.CreateFlightRequest;
import com.example.demo.DTO.Request.CreateFlightSegmentRequest;
import com.example.demo.Mapper.AdminFlightMapper;
import com.example.demo.Mapper.FlightSegmentMapper;
import com.example.demo.Model.entities.*;
import com.example.demo.repository.AirPlaneRepository;
import com.example.demo.repository.AirportRepository;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.FlightSegmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminFlightService {

    private final AdminAuthHelper adminAuthHelper;
    private final AuthService authService;
    private final AirPlaneRepository airPlaneRepository;
    private final FlightRepository flightRepository;
    private final AdminFlightMapper adminFlightMapper;
    private final FlightSegmentRepository flightSegmentRepository;
    private final AirportRepository airportRepository;
    private final FlightSegmentMapper flightSegmentMapper;

    public AdminFlightService(AdminAuthHelper adminAuthHelper,
                              AuthService authService,
                              AirPlaneRepository airPlaneRepository,
                              FlightRepository flightRepository,
                              FlightSegmentRepository flightSegmentRepository,
                              AirportRepository airportRepository,
                              AdminFlightMapper adminFlightMapper,
                              FlightSegmentMapper flightSegmentMapper) {
        this.adminAuthHelper = adminAuthHelper;
        this.authService = authService;
        this.airPlaneRepository = airPlaneRepository;
        this.flightRepository = flightRepository;
        this.flightSegmentRepository = flightSegmentRepository;
        this.airportRepository = airportRepository;
        this.adminFlightMapper = adminFlightMapper;
        this.flightSegmentMapper = flightSegmentMapper;
    }

    @Transactional
    public Long createFlight(String auth, CreateFlightRequest req) {
        adminAuthHelper.requireAdmin(auth);

        if (req.getFlightNumber() == null || req.getFlightNumber().isBlank())
            throw new RuntimeException("flightNumber required");
        if (req.getAirplaneId() == null)
            throw new RuntimeException("airplaneId required");

        AirPlane airplane = airPlaneRepository.findById(req.getAirplaneId()).orElse(null);
        if (airplane == null) throw new RuntimeException("airplane not found");

        Long staffId = authService.requireUserId(auth);

        Flight f = adminFlightMapper.toFlight(req);

        // relations
        f.setAirplane(airplane);

        Staff staff = new Staff();
        staff.setUserId(staffId);
        f.setCreatedByStaff(staff);

        return flightRepository.save(f).getId();
    }



    @Transactional
    public Long createFlightSegment(String auth, CreateFlightSegmentRequest req) {
        adminAuthHelper.requireAdmin(auth);

        // validations (same as yours)
        if (req.getFlightId() == null) throw new RuntimeException("flightId required");
        if (req.getSegmentNo() == null || req.getSegmentNo() <= 0) throw new RuntimeException("segmentNo required");
        if (req.getFromAirportId() == null || req.getToAirportId() == null) throw new RuntimeException("from/to required");
        if (req.getFromAirportId().equals(req.getToAirportId())) throw new RuntimeException("from and to must differ");
        if (req.getDepartureTime() == null || req.getArrivalTime() == null) throw new RuntimeException("times required");
        if (!req.getArrivalTime().isAfter(req.getDepartureTime())) throw new RuntimeException("arrival must be after departure");

        if (!flightRepository.existsById(req.getFlightId())) throw new RuntimeException("flight not found");
        if (!airportRepository.existsById(req.getFromAirportId())) throw new RuntimeException("from airport not found");
        if (!airportRepository.existsById(req.getToAirportId())) throw new RuntimeException("to airport not found");

        FlightSegment seg = flightSegmentMapper.toSegment(req);

        // set relations using IDs
        Flight f = new Flight();
        f.setId(req.getFlightId());
        seg.setFlight(f);

        AirPort from = new AirPort();
        from.setId(req.getFromAirportId());
        seg.setFromAirport(from);

        AirPort to = new AirPort();
        to.setId(req.getToAirportId());
        seg.setToAirport(to);

        return flightSegmentRepository.save(seg).getId();
    }
}
