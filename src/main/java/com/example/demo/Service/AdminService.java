package com.example.demo.Service;


import com.example.demo.DTO.Request.CreateFlightRequest;
import com.example.demo.DTO.Request.CreateFlightSegmentRequest;
import com.example.demo.DTO.Request.CreateStaffRequest;
import com.example.demo.Mapper.FlightMapper;
import com.example.demo.Model.entities.*;
import com.example.demo.Model.enums.StaffRole;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;
    private final AirPlaneRepository airPlaneRepository;
    private final FlightRepository flightRepository;
    FlightSegmentRepository flightSegmentRepository;
    AirportRepository airportRepository;


    public AdminService(AuthService authService, UserRepository userRepository, StaffRepository staffRepository, AirPlaneRepository airPlaneRepository, FlightRepository flightRepository
    , FlightSegmentRepository flightSegmentRepository, AirportRepository airportRepository) {

        this.authService = authService;
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.airPlaneRepository = airPlaneRepository;
        this.flightRepository = flightRepository;
        this.flightSegmentRepository = flightSegmentRepository;
        this.airportRepository = airportRepository;
    }

    private void requireAdmin(String authorizationHeader) {
        Long userId = authService.requireUserId(authorizationHeader);

        Staff staff = staffRepository.findByUserId(userId);
        if (staff == null || staff.getRole() != StaffRole.ADMIN) {
            throw new RuntimeException("Forbidden: ADMIN only");
        }
    }

    @Transactional
    public Long createStaff(String authorizationHeader, CreateStaffRequest req) {
        requireAdmin(authorizationHeader);

        if (req.getEmail() == null || req.getPassword() == null) {
            throw new RuntimeException("email and password required");
        }
        if (req.getRole() == null) {
            throw new RuntimeException("role is required");
        }
        if (req.getEmployeeCode() == null) {
            throw new RuntimeException("employeeCode is required");
        }

        User existing = userRepository.findByEmail(req.getEmail());
        if (existing != null) {
            throw new RuntimeException("email already exists");
        }

        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setPasswordHash(req.getPassword());
        user = userRepository.save(user);

        Staff staff = new Staff();
        staff.setUserId(user.getId());
        staff.setRole(req.getRole());
        staff.setEmployeeCode(req.getEmployeeCode());
        staff.setJobTitle(req.getJobTitle());
        staff.setLicenseNo(req.getLicenseNo());
        staff.setIsEnabled(true);

        staffRepository.save(staff);

        return user.getId();
    }

    @Transactional
    public Long createFlight(String auth, CreateFlightRequest req) {
        requireAdmin(auth);

        if (req.getFlightNumber() == null || req.getFlightNumber().isBlank()) {
            throw new RuntimeException("flightNumber required");
        }
        if (req.getAirplaneId() == null) {
            throw new RuntimeException("airplaneId required");
        }

        // verify airplane exists
        //use optional
        AirPlane airplane = airPlaneRepository.findById(req.getAirplaneId()).orElse(null);
        if (airplane == null) {
            throw new RuntimeException("airplane not found");
        }

        Long staffId = authService.requireUserId(auth);

        Flight f = FlightMapper.toEntity(req, staffId);

        f.setAirplane(airplane);

        return flightRepository.save(f).getId();
    }


    @Transactional
    public Long createFlightSegment(String auth, CreateFlightSegmentRequest req) {
        requireAdmin(auth);

        if (req.getFlightId() == null) throw new RuntimeException("flightId required");
        if (req.getSegmentNo() == null || req.getSegmentNo() <= 0) throw new RuntimeException("segmentNo required");
        if (req.getFromAirportId() == null || req.getToAirportId() == null) throw new RuntimeException("from/to required");
        if (req.getFromAirportId().equals(req.getToAirportId())) throw new RuntimeException("from and to must differ");
        if (req.getDepartureTime() == null || req.getArrivalTime() == null) throw new RuntimeException("times required");
        if (!req.getArrivalTime().isAfter(req.getDepartureTime())) throw new RuntimeException("arrival must be after departure");

        if (!flightRepository.existsById(req.getFlightId())) throw new RuntimeException("flight not found");
        if (!airportRepository.existsById(req.getFromAirportId())) throw new RuntimeException("from airport not found");
        if (!airportRepository.existsById(req.getToAirportId())) throw new RuntimeException("to airport not found");

        FlightSegment seg = com.example.demo.Mapper.FlightSegmentMapper.toEntity(req);
        return flightSegmentRepository.save(seg).getId();
    }

}
