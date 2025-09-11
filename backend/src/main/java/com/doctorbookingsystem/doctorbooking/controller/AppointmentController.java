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

    AppointmentService appointmentService;

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
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable String appointmentId) {
        log.info("Fetching appointment with id: {}", appointmentId);
         AppointmentDTO appointment = appointmentService.getAppointmentById(appointmentId);
         return ResponseEntity.ok(appointment);
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctorId(@PathVariable String doctorId) {
        log.info("Fetching appointments for doctor with id: {}", doctorId);
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }
    @PutMapping("{doctorId}/update-status/{appointmentId}")
    public ResponseEntity<AppointmentDTO> updateAppointmentStatus(
            @PathVariable String doctorId,
            @PathVariable String appointmentId,
            @RequestParam AppointmentStatus status) {
        log.info("Updating appointment status for appointmentId: {} by doctorId: {}", appointmentId, doctorId);
        AppointmentDTO appointmentDTO = appointmentService.updateAppointmentStatus(doctorId, appointmentId, status);
        return ResponseEntity.ok(appointmentDTO);
    }

}
