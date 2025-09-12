package com.doctorbookingsystem.doctorbooking.model;

import com.doctorbookingsystem.doctorbooking.enums.Gender;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a patient in the doctor booking system.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    private String id;
    private String fullName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private List<String> allergies;
    private List<String> medicalHistory;
    private String insuranceNumber;
}
