package com.doctorbookingsystem.doctorbooking.service.interfaces;

import com.doctorbookingsystem.doctorbooking.dto.AppointmentDTO;
import com.doctorbookingsystem.doctorbooking.enums.AppointmentStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {

    /**
     * Retrieve all appointments.
     */
    List<AppointmentDTO> getAllAppointments();

    /**
     * Retrieve an appointment by its ID.
     */
    AppointmentDTO getAppointmentById(String appointmentId);

    /**
     * Retrieve appointments by doctor ID.
     */
    List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId);

    /**
     * Update the status of an appointment.
     */
    AppointmentDTO updateAppointmentStatus(String doctorId, String appointmentId, AppointmentStatus status);
}
