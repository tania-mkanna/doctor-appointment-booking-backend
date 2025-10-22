package com.doctorbookingsystem.doctorbooking.dto;

import com.doctorbookingsystem.doctorbooking.enums.AppointmentPriority;
import com.doctorbookingsystem.doctorbooking.enums.CaseType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAppointmentRequest {
    @NotNull
    private String doctorId;

    @NotNull
    private String patientId;

    @NotNull
    private String slotId;

    @NotNull
    private CaseType caseType;

    private String notes;

    private AppointmentPriority priority;
}
