package com.doctorbookingsystem.doctorbooking.repository;

import com.doctorbookingsystem.doctorbooking.dto.*;

public interface AuthService {
    AuthenticationResponse patientRegister(PatientRegisterRequest request);
    AuthenticationResponse DoctorRegister(DoctorRegisterRequest request);
    AuthenticationResponse authenticate(LoginRequest request);
}
