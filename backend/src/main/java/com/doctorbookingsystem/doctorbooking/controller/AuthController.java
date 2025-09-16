package com.doctorbookingsystem.doctorbooking.controller;

import com.doctorbookingsystem.doctorbooking.dto.AuthenticationResponse;
import com.doctorbookingsystem.doctorbooking.dto.DoctorRegisterRequest;
import com.doctorbookingsystem.doctorbooking.dto.LoginRequest;
import com.doctorbookingsystem.doctorbooking.dto.PatientRegisterRequest;
import com.doctorbookingsystem.doctorbooking.repository.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register-patient")
    public ResponseEntity<AuthenticationResponse> registerPatient(@Valid @RequestBody PatientRegisterRequest request){
        return ResponseEntity.ok(authService.patientRegister(request));
    }
    @PostMapping("/register-doctor")
    public ResponseEntity<AuthenticationResponse> registerDoctor(@Valid @RequestBody DoctorRegisterRequest request){
        return ResponseEntity.ok(authService.DoctorRegister(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
