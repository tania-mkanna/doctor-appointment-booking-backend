package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.dto.AppointmentDTO;
import com.doctorbookingsystem.doctorbooking.dto.UserDTO;
import com.doctorbookingsystem.doctorbooking.enums.AppointmentPriority;
import com.doctorbookingsystem.doctorbooking.enums.AppointmentStatus;
import com.doctorbookingsystem.doctorbooking.exception.InvalidRequestException;
import com.doctorbookingsystem.doctorbooking.exception.NotFoundException;
import com.doctorbookingsystem.doctorbooking.mapper.AppointmentMapper;
import com.doctorbookingsystem.doctorbooking.model.*;
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
    private final AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, UserService userService, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
        this.appointmentMapper = appointmentMapper;
    }
    @Override
    public List<AppointmentDTO> getAllAppointments() {
        log.info("Finding all appointments");
        return appointmentRepository.findAll().stream()
                .map(appointment -> appointmentMapper.toDto(appointment))
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentById(String appointmentId) {
        log.info("Finding appointment with id: {}", appointmentId);
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found with id: " + appointmentId));
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId) {
        log.info("Finding appointments for doctor with id: {}", doctorId);

        UserDTO doctorUser = userService.findUserById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + doctorId));

        return appointmentRepository.findByDoctorId(doctorId).stream()
                .map(appointment -> appointmentMapper.toDto(appointment))
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> getAppointmentsByPatientId(String patientId) {
        log.info("Finding appointments for patient with id: {}", patientId);
        UserDTO patientUser = userService.findUserById(patientId)
            .orElseThrow(() -> new NotFoundException("Patient not found with id: " + patientId));

        return appointmentRepository.findByPatientId(patientId).stream()
                .map(appointment -> appointmentMapper.toDto(appointment))
                .collect(Collectors.toList());
    }
    @Override
    public AppointmentDTO confirmAppointment(String doctorId, String appointmentId) {
        log.info("Confirming appointmentId: {} by doctorId: {}", appointmentId, doctorId);

        Appointment appointment = getAndValidateDoctorAppointment(doctorId, appointmentId);

        if (appointment.getStatus() != AppointmentStatus.REQUESTED) {
            throw new InvalidRequestException("Only appointments in REQUESTED status can be confirmed.");
        }

        appointment.setStatus(AppointmentStatus.CONFIRMED);
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentDTO declineAppointment(String doctorId, String appointmentId) {
        log.info("Declining appointmentId: {} by doctorId: {}", appointmentId, doctorId);

        Appointment appointment = getAndValidateDoctorAppointment(doctorId, appointmentId);

        if (appointment.getStatus() != AppointmentStatus.REQUESTED) {
            throw new InvalidRequestException("Only appointments in REQUESTED status can be declined.");
        }

        appointment.setStatus(AppointmentStatus.DECLINED);
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentDTO cancelAppointment(String patientId, String appointmentId) {
        log.info("Cancelling appointmentId: {} by patientId: {}", appointmentId, patientId);


        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found with id: " + appointmentId));

        UserDTO patientUser = userService.findUserById(patientId)
                .orElseThrow(() -> new NotFoundException("Patient not found with id: " + patientId));

        appointment.setStatus(AppointmentStatus.CANCELLED);
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    public AppointmentDTO assignPriority(String appointmentId, AppointmentPriority priority){
        log.info("Assigning priority: {} to appointmentId: {}", priority, appointmentId);

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found with id: " + appointmentId));

        appointment.setPriority(priority);

        if (priority == AppointmentPriority.HIGH) {
            reorderDoctorAppointments(appointment.getDoctor().getId());
        }

        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    private void reorderDoctorAppointments(String doctorId) {
        List<Appointment> doctorAppointments = appointmentRepository.findByDoctorId(doctorId);

        doctorAppointments.sort((a1, a2) -> {
            if (a1.getPriority() == a2.getPriority()) {
                return a1.getSlot().getStart().compareTo(a2.getSlot().getStart());
            }
            if (a1.getPriority() == AppointmentPriority.HIGH) return -1;
            if (a2.getPriority() == AppointmentPriority.HIGH) return 1;
            if (a1.getPriority() == AppointmentPriority.MEDIUM && a2.getPriority() == AppointmentPriority.LOW) return -1;
            if (a1.getPriority() == AppointmentPriority.LOW && a2.getPriority() == AppointmentPriority.MEDIUM) return 1;
            return 0;
        });

        List<AvailabilitySlot> slots = doctorAppointments.stream()
                .map(Appointment::getSlot)
                .sorted((s1, s2) -> s1.getStart().compareTo(s2.getStart()))
                .collect(Collectors.toList());

        for (int i = 0; i < doctorAppointments.size(); i++) {
            Appointment a = doctorAppointments.get(i);
            AvailabilitySlot newSlot = slots.get(i);
            if (!a.getSlot().getId().equals(newSlot.getId())) {
                a.setSlot(newSlot);
                notifyPatientAboutReschedule(a);
                appointmentRepository.save(a);
            }
        }
    }
    /**
     * Simulated notification method – replace with real email/SMS later.
     */
    private void notifyPatientAboutReschedule(Appointment appointment) {
        String patientId = appointment.getPatient().getId();

        UserDTO user = userService.findUserById(patientId)
                .orElseThrow(() -> new NotFoundException("Patient not found with id: " + patientId));

        String patientEmail = user.getEmail();
        log.info("Notifying patient {} at {} about reschedule of appointment {}",
                appointment.getPatient().getId(), patientEmail, appointment.getId());

        // Here you would inject an EmailService or NotificationService
        // emailService.sendRescheduleNotification(patientEmail, appointment);
    }
    private Appointment getAndValidateDoctorAppointment(String doctorId, String appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found with id: " + appointmentId));

        UserDTO doctorUser = userService.findUserById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + doctorId));

        if (doctorUser.getDoctor() == null) {
            throw new NotFoundException("User with id: " + doctorId + " is not a doctor");
        }
        return appointment;
    }




}
