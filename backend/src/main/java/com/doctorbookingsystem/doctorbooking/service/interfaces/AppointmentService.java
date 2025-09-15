package com.doctorbookingsystem.doctorbooking.service.interfaces;

import com.doctorbookingsystem.doctorbooking.dto.AppointmentDTO;
import com.doctorbookingsystem.doctorbooking.enums.AppointmentPriority;
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
     * Retrieve appointments by patient ID.
     */
    List<AppointmentDTO> getAppointmentsByPatientId(String patientId);

    AppointmentDTO confirmAppointment(String doctorId, String appointmentId);

    AppointmentDTO declineAppointment(String doctorId, String appointmentId);
    /**
     * Cancel an appointment by a patient.
     */
    AppointmentDTO cancelAppointment(String patientId, String appointmentId);

    AppointmentDTO assignPriority(String appointmentId, AppointmentPriority priority);

}
