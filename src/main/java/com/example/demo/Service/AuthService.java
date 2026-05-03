package com.example.demo.Service;

import com.example.demo.DTO.Request.CreateCustomerRequest;
import com.example.demo.DTO.Request.LoginRequest;
import com.example.demo.DTO.Response.LoginResponse;
import com.example.demo.Model.entities.Customer;
import com.example.demo.Model.entities.User;
import com.example.demo.Util.JwtService;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       CustomerRepository customerRepository,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    public void registerCustomer(CreateCustomerRequest req) {
        userRepository.findByEmail(req.getEmail())
                .ifPresent(u -> { throw new RuntimeException("Email already exists"); });

        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPasswordHash(req.getPassword()); // (later: encode!)
        user.setPhone(req.getPhone());

        User savedUser = userRepository.save(user);

        Customer customer = new Customer();
        customer.setUserId(savedUser.getId());
        customer.setDateOfBirth(req.getDateOfBirth());

        customerRepository.save(customer);
    }

    public LoginResponse login(LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("invalid email"));

        if (!user.getPasswordHash().equals(req.getPassword())) {
            throw new RuntimeException("invalid password");
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                Map.of("userId", user.getId())
        );

        return new LoginResponse(token, user.getId(), user.getFullName(), user.getEmail());
    }
}
