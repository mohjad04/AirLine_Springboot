package com.example.demo.Controller;



import com.example.demo.DTO.Request.CreateCustomerRequest;
import com.example.demo.DTO.Request.LoginRequest;
import com.example.demo.DTO.Request.RegisterRequest;
import com.example.demo.DTO.Response.LoginResponse;
import com.example.demo.Service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @PostMapping("/register/customer")
    public String register(@RequestBody CreateCustomerRequest req) {
        authService.registerCustomer(req);
        return "Customer registered successfully";
    }

}
