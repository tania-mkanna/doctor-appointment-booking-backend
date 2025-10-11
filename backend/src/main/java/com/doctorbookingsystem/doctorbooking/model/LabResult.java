package com.doctorbookingsystem.doctorbooking.model;

import com.doctorbookingsystem.doctorbooking.enums.LabResultStatus;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabResult {
    private String testName;
    private LabResultStatus status; //PENDING,COMPLETED
    private Instant orderDate;
    private Instant completedDate;
    private String resultUrl;
}
