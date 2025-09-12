package com.doctorbookingsystem.doctorbooking.dto;

import com.doctorbookingsystem.doctorbooking.enums.AppointmentStatus;
import com.doctorbookingsystem.doctorbooking.enums.AppointmentPriority;
import com.doctorbookingsystem.doctorbooking.enums.CaseType;
import com.doctorbookingsystem.doctorbooking.model.AvailabilitySlot;
import com.doctorbookingsystem.doctorbooking.model.Doctor;
import com.doctorbookingsystem.doctorbooking.model.Patient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Data Transfer Object for Appointment.
 */
@Data
public class AppointmentDTO {

    private String id;
    private Doctor doctor;

    private Patient patient;

    private AvailabilitySlot slot;

    private AppointmentStatus status;

    @NotNull(message = "Case type is required")
    private CaseType caseType;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    private String notes;

    private AppointmentPriority priority;
}
