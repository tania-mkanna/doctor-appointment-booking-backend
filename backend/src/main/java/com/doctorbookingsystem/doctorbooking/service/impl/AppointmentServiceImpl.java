package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.dto.AppointmentDTO;
import com.doctorbookingsystem.doctorbooking.dto.UserDTO;
import com.doctorbookingsystem.doctorbooking.enums.AppointmentStatus;
import com.doctorbookingsystem.doctorbooking.exception.NotFoundException;
import com.doctorbookingsystem.doctorbooking.mapper.AppointmentMapper;
import com.doctorbookingsystem.doctorbooking.model.Appointment;
import com.doctorbookingsystem.doctorbooking.model.Doctor;
import com.doctorbookingsystem.doctorbooking.model.Patient;
import com.doctorbookingsystem.doctorbooking.repository.AppointmentRepository;
import com.doctorbookingsystem.doctorbooking.service.interfaces.AppointmentService;
import com.doctorbookingsystem.doctorbooking.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;
    AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, UserService userService, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
        this.appointmentMapper = appointmentMapper;
    }
    @Override
    public List<AppointmentDTO> getAllAppointments() {
        log.info("Finding all appointments");
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
        UserDTO userDTO = userService.findUserById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));
        Doctor doctor = userDTO.getDoctor();

        if (doctor == null) {
            throw new RuntimeException("User with id: " + doctorId + " is not a doctor");
        }
        List<String> appointmentsIds = doctor.getAppointmentsIds();
        List<Appointment> appointments = appointmentRepository.findAllById(appointmentsIds);
        return appointments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> getAppointmentsByPatientId(String patientId) {
        log.info("Finding appointments for patient with id: {}", patientId);
    UserDTO userDTO = userService.findUserById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));
        Patient patient = userDTO.getPatient();
        if (patient == null) {
            throw new RuntimeException("User with id: " + patientId + " is not a patient");
        }
        List<String> appointmentsIds = patient.getAppointmentsIds();
        List<Appointment> appointments = appointmentRepository.findAllById(appointmentsIds);
        return appointments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public AppointmentDTO confirmAppointment(String doctorId, String appointmentId) {
        log.info("Confirming appointmentId: {} by doctorId: {}", appointmentId, doctorId);

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

        Doctor doctor = userService.findUserById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId))
                .getDoctor();

        if (doctor == null) {
            throw new NotFoundException("User with id: " + doctorId + " is not a doctor");
        }

        if (appointment.getStatus() != AppointmentStatus.REQUESTED) {
            throw new IllegalStateException("Only appointments in REQUESTED status can be confirmed.");
        }

        appointment.setStatus(AppointmentStatus.CONFIRMED);
        Appointment updatedAppointment = appointmentRepository.save(appointment);

        return toDto(updatedAppointment);
    }

    @Override
    public AppointmentDTO declineAppointment(String doctorId, String appointmentId) {
        log.info("Declining appointmentId: {} by doctorId: {}", appointmentId, doctorId);

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

        Doctor doctor = userService.findUserById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId))
                .getDoctor();

        if (doctor == null) {
            throw new NotFoundException("User with id: " + doctorId + " is not a doctor");
        }

        if (appointment.getStatus() != AppointmentStatus.REQUESTED) {
            throw new IllegalStateException("Only appointments in REQUESTED status can be declined.");
        }

        appointment.setStatus(AppointmentStatus.DECLINED);
        Appointment updatedAppointment = appointmentRepository.save(appointment);

        return toDto(updatedAppointment);
    }

    private AppointmentDTO toDto(Appointment appointment) {
        return appointmentMapper.toDto(appointment);
    }
    private Appointment toEntity(AppointmentDTO appointmentDTO) {
        return appointmentMapper.toEntity(appointmentDTO);
    }

    @Override
    public AppointmentDTO cancelAppointment(String patientId, String appointmentId) {
        log.info("Cancelling appointmentId: {} by patientId: {}", appointmentId, patientId);

        // 1. Find appointment
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

        // 2. Validate patient
        Patient patient = userService.findUserById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId))
                .getPatient();

        if (patient == null) {
            throw new NotFoundException("User with id: " + patientId + " is not a patient");
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);
        Appointment updatedAppointment = appointmentRepository.save(appointment);

        return toDto(updatedAppointment);
    }

}
