package com.doctorbookingsystem.doctorbooking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class AvailabilitySlotDTO {

    @NotNull(message="Doctor ID is required")
    private String doctorId;

    @NotNull(message="Start time is required")
    private Instant start;

    @NotNull(message="End time is required")
    private Instant end;

    private boolean booked =false;
}
