package com.doctorbookingsystem.doctorbooking.controller;


import com.doctorbookingsystem.doctorbooking.dto.DoctorPatientViewDTO;
import com.doctorbookingsystem.doctorbooking.dto.UserDTO;
import com.doctorbookingsystem.doctorbooking.service.interfaces.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing user-related operations.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
        
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
