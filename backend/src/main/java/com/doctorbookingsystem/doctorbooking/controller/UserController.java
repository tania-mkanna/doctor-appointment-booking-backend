package com.doctorbookingsystem.doctorbooking.controller;

import com.doctorbookingsystem.doctorbooking.dto.UserDTO;
import com.doctorbookingsystem.doctorbooking.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing user-related operations.
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/doctor/{doctorId}/profile")
    public ResponseEntity<UserDTO> getDoctorProfile(@PathVariable("doctorId") String doctorId){
        log.info("Fetching profile of doctorId {}",doctorId);


        return ResponseEntity.ok(userService.getDoctorProfile(doctorId));
    }
    @GetMapping("/patient/{patientId}/profile")
    public ResponseEntity<UserDTO> getPatientProfile(@PathVariable("patientId") String patientId){
        log.info("Fetching profile of patientId {}",patientId);

        return ResponseEntity.ok(userService.getPatientProfile(patientId));
    }
}
