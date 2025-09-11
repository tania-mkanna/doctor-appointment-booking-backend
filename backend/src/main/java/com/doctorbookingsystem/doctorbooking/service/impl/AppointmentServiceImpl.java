package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.dto.AppointmentDTO;
import com.doctorbookingsystem.doctorbooking.enums.AppointmentStatus;
import com.doctorbookingsystem.doctorbooking.mapper.AppointmentMapper;
import com.doctorbookingsystem.doctorbooking.model.Appointment;
import com.doctorbookingsystem.doctorbooking.repository.AppointmentRepository;
import com.doctorbookingsystem.doctorbooking.service.interfaces.AppointmentService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    AppointmentRepository appointmentRepository;
    AppointmentMapper appointmentMapper;
    @Override
    public List<AppointmentDTO> getAllAppointments() {
        log.debug("Finding all appointments");
        return appointmentRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentById(String appointmentId) {
        log.info("Finding appointment with id: {}", appointmentId);
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));
        return toDto(appointment);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId) {
        log.info("Finding appointments for doctor with id: {}", doctorId);
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
        return appointments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO updateAppointmentStatus(String doctorId, String appointmentId, AppointmentStatus status) {
        log.info("Updating appointment status for appointmentId: {} by doctorId: {}", appointmentId, doctorId);
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

        if (!appointment.getDoctorId().equals(doctorId)) {
            throw new RuntimeException("Doctor is not authorized to update this appointment");
        }

        appointment.setStatus(status);
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return toDto(updatedAppointment);
    }

    private AppointmentDTO toDto(Appointment appointment) {
        return appointmentMapper.toDto(appointment);
    }
}
