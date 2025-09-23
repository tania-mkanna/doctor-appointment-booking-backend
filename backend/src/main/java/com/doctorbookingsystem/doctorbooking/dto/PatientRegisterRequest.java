package com.doctorbookingsystem.doctorbooking.dto;

import com.doctorbookingsystem.doctorbooking.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegisterRequest extends RegisterRequest{
    private String fullName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private List<String> allergies;
    private List<String> medicalHistory;
    private String insuranceNumber; // optional
}
