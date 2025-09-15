package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.dto.AuthenticationResponse;
import com.doctorbookingsystem.doctorbooking.dto.LoginRequest;
import com.doctorbookingsystem.doctorbooking.dto.RegisterRequest;
import com.doctorbookingsystem.doctorbooking.model.CustomUserDetails;
import com.doctorbookingsystem.doctorbooking.model.User;
import com.doctorbookingsystem.doctorbooking.repository.AuthService;
import com.doctorbookingsystem.doctorbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtServiceImpl jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user= User.builder().fullName(request.getName())
                .email(request.getEmail()).role(request.getRole()).passwordHash(passwordEncoder.encode(request.getPassword())).build();
        userRepository.save(user);
        CustomUserDetails userDetails= CustomUserDetails.builder().user(user)
                .authorities(List.of(() -> user.getRole().name()))
                .build();
        var jwtToken=jwtService.generatedToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        var user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User Not Found"));
        CustomUserDetails userDetails= CustomUserDetails.builder().user(user)
                .authorities(List.of(() -> user.getRole().name()))
                .build();
        var jwtToken=jwtService.generatedToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
