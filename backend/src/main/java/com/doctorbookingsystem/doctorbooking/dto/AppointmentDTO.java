package com.doctorbookingsystem.doctorbooking.dto;

import com.doctorbookingsystem.doctorbooking.enums.AppointmentStatus;
import com.doctorbookingsystem.doctorbooking.enums.AppointmentPriority;
import com.doctorbookingsystem.doctorbooking.enums.CaseType;
import com.doctorbookingsystem.doctorbooking.model.Slot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.Instant;

@Data
public class AppointmentDTO {

    private String id;
    @NotNull(message = "Patient ID is required")
    @NotBlank(message = "Patient ID cannot be empty")
    private String patientId;

    @NotNull(message = "Doctor ID is required")
    @NotBlank(message = "Doctor ID cannot be empty")
    private String doctorId;

    @NotNull(message = "Slot ID is required")
    @NotBlank(message = "Slot ID cannot be empty")
    private String slotId;

    private AppointmentStatus status;

    @NotNull(message = "Case type is required")
    private CaseType caseType;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    private String notes;

    private AppointmentPriority priority;
}
