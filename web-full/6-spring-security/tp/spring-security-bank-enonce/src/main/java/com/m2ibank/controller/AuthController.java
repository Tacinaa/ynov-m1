package com.m2ibank.controller;

import com.m2ibank.dto.UserLoginDto;
import com.m2ibank.dto.JwtResponseDto;
import com.m2ibank.model.Customer;
import com.m2ibank.repository.CustomerRepository;
import com.m2ibank.config.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody Customer customer) {
        customer.setPwd(passwordEncoder.encode(customer.getPwd()));
        customerRepository.save(customer);
        return "Utilisateur enregistré avec succès";
    }

    @PostMapping("/login")
    public JwtResponseDto login(@RequestBody UserLoginDto request) throws Exception {
        Customer customer = customerRepository.findByEmail(request.getEmail());
        if (customer == null || !passwordEncoder.matches(request.getPassword(), customer.getPwd())) {
            throw new Exception("Identifiants invalides");
        }
        String token = jwtUtil.generateToken(customer);
        return new JwtResponseDto(token);
    }
}