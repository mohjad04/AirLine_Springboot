package com.example.demo.Service;

import com.example.demo.DTO.Request.CreateCustomerRequest;
import com.example.demo.DTO.Request.LoginRequest;
import com.example.demo.DTO.Request.RegisterRequest;
import com.example.demo.DTO.Response.LoginResponse;
import com.example.demo.Model.entities.Customer;
import com.example.demo.Model.entities.User;
import com.example.demo.Util.TokenStore;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    public AuthService(UserRepository userRepository , CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }


    public void registerCustomer(CreateCustomerRequest req) {

        User existing = userRepository.findByEmail(req.getEmail());
        if (existing != null) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPasswordHash(req.getPassword());
        user.setPhone(req.getPhone());

        User savedUser = userRepository.save(user);

        Customer customer = new Customer();
        customer.setUserId(savedUser.getId());
        customer.setDateOfBirth(req.getDateOfBirth());

        customerRepository.save(customer);
    }



    // LOGIN
    public LoginResponse login(LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail());

        if (user == null) {
            throw new RuntimeException("invalid email");
        }

        if (!user.getPasswordHash().equals(req.getPassword())) {
            throw new RuntimeException("invalid password");
        }

        String token = TokenStore.createToken(user.getId());
        return new LoginResponse(token, user.getId(), user.getFullName(), user.getEmail());
    }

    public Long requireUserId(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Token=")) {
            throw new RuntimeException("Missing Authorization header");
        }
        String token = authHeader.substring(6);
        Long userId = TokenStore.getUserId(token);
        if (userId == null) throw new RuntimeException("Invalid token");
        return userId;
    }
}
