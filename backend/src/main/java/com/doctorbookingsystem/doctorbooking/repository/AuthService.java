package com.doctorbookingsystem.doctorbooking.repository;

import com.doctorbookingsystem.doctorbooking.dto.AuthenticationResponse;
import com.doctorbookingsystem.doctorbooking.dto.LoginRequest;
import com.doctorbookingsystem.doctorbooking.dto.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(LoginRequest request);
}
