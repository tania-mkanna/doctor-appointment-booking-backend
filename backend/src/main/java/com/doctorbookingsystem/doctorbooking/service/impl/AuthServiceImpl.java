package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.dto.*;
import com.doctorbookingsystem.doctorbooking.enums.Role;
import com.doctorbookingsystem.doctorbooking.model.CustomUserDetails;
import com.doctorbookingsystem.doctorbooking.model.Doctor;
import com.doctorbookingsystem.doctorbooking.model.Patient;
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
    public AuthenticationResponse patientRegister(PatientRegisterRequest request) {
        var patient= Patient.builder().fullName(request.getFullName())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .allergies(request.getAllergies())
                .medicalHistory(request.getMedicalHistory())
                .insuranceNumber(request.getInsuranceNumber())
                .build();
        var user= User.builder().userName(request.getName())
                .email(request.getEmail()).role(Role.PATIENT).password(passwordEncoder.encode(request.getPassword()))
                .patient(patient)
                .build();
        userRepository.save(user);
        CustomUserDetails userDetails= CustomUserDetails.builder().user(user)
                .authorities(List.of(() -> user.getRole().name()))
                .build();
        var jwtToken=jwtService.generatedToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse DoctorRegister(DoctorRegisterRequest request) {
        var doctor= Doctor.builder()
                .fullName(request.getFullName())
                .gender(request.getGender())
                .languages(request.getLanguages())
                .yearsOfExperience(request.getYearsOfExperience())
                .bio(request.getBio())
                .clinicLocation(request.getClinicLocation())
                .city(request.getCity())
                .services(request.getServices())
                .specialties(request.getSpecialties()).build();
        var user= User.builder().userName(request.getName())
                .email(request.getEmail()).role(Role.DOCTOR).password(passwordEncoder.encode(request.getPassword()))
                .doctor(doctor)
                .build();
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
