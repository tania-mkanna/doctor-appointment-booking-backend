package com.doctorbookingsystem.doctorbooking.controller;

import com.doctorbookingsystem.doctorbooking.dto.DoctorPatientViewDTO;
import com.doctorbookingsystem.doctorbooking.dto.UserDTO;
import com.doctorbookingsystem.doctorbooking.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // implement searchDoctors endpoint
    @GetMapping("/doctors/search")
    public List<DoctorPatientViewDTO> searchDoctors(@RequestParam(required = false)  String text) {
        return userService.searchDoctors(text);
    }

    // implement findNearbyDoctors endpoint
    @GetMapping("/doctors/nearby")
    public List<DoctorPatientViewDTO> findNearbyDoctors(@RequestParam double latitude, @RequestParam double longitude) {
        return userService.findNearbyDoctors(latitude, longitude);
    }
}
