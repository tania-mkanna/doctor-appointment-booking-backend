package com.doctorbookingsystem.doctorbooking.model;

import com.doctorbookingsystem.doctorbooking.enums.PrescriptionStatus;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {

    private String medicineName;
    private String frequency;
    private String dosage;
    private PrescriptionStatus status; //ACTIVE,EXPIRED
    private Instant prescribedDate;
    private Instant endDate;
}
