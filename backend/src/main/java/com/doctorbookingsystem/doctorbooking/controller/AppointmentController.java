package com.doctorbookingsystem.doctorbooking.controller;

import com.doctorbookingsystem.doctorbooking.dto.AppointmentDTO;
import com.doctorbookingsystem.doctorbooking.enums.AppointmentStatus;
import com.doctorbookingsystem.doctorbooking.service.interfaces.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/appointments")
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    /**
     * Retrieve all appointments.
     */
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        log.info("Fetching all appointments");
        List<AppointmentDTO> appointments =  appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    /**
     * Retrieve an appointment by its ID.
     */
    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable String appointmentId) {
        log.info("Fetching appointment with id: {}", appointmentId);
         AppointmentDTO appointment = appointmentService.getAppointmentById(appointmentId);
         return ResponseEntity.ok(appointment);
    }

    /**
     * Retrieve all appointments for a specific doctor by doctor ID.
     */
    @GetMapping("/{doctorId}")// we have to put preauthorize for doctor so only doctor can see his appointments
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctorId(@PathVariable String doctorId) {
        log.info("Fetching appointments for doctor with id: {}", doctorId);
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }

    /**
     * Retrieve all appointments for a specific doctor by doctor ID.
     */
    @GetMapping("/{patientId}")// we have to put preauthorize for patient so only patient can see his appointments
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByPatientId(@PathVariable String patientId) {
        log.info("Fetching appointments for doctor with id: {}", patientId);
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }
    /**
     * Confirm an appointment by doctor.
     */
    @PutMapping("{doctorId}/{appointmentId}/confirm")
    public ResponseEntity<AppointmentDTO> confirmAppointment(
            @PathVariable String doctorId,
            @PathVariable String appointmentId) {
        return ResponseEntity.ok(appointmentService.confirmAppointment(doctorId, appointmentId));
    }

    /**
     * Decline an appointment by doctor.
     */
    @PutMapping("{doctorId}/{appointmentId}/decline")
    public ResponseEntity<AppointmentDTO> declineAppointment(
            @PathVariable String doctorId,
            @PathVariable String appointmentId) {
        return ResponseEntity.ok(appointmentService.declineAppointment(doctorId, appointmentId));
    }

    /**
     * Cancel an appointment by a patient.
     */
    @PutMapping("/{patientId}/{appointmentId}/cancel")
    public ResponseEntity<AppointmentDTO> cancelAppointment(
            @PathVariable String patientId,
            @PathVariable String appointmentId) {
        AppointmentDTO appointmentDTO = appointmentService.cancelAppointment(patientId, appointmentId);
        return ResponseEntity.ok(appointmentDTO);
    }
}
