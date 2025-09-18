package com.doctorbookingsystem.doctorbooking.controller;

import com.doctorbookingsystem.doctorbooking.dto.DocumentReferenceDto;
import com.doctorbookingsystem.doctorbooking.dto.UserDTO;
import com.doctorbookingsystem.doctorbooking.enums.DocumentType;
import com.doctorbookingsystem.doctorbooking.model.DocumentReference;
import com.doctorbookingsystem.doctorbooking.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    /**
     * Retrieve a user by their ID.
     */

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String userId) {
        log.info("Fetching user with id: {}", userId);
        UserDTO user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Upload a document for a doctor.
     */

    @PostMapping("/api/doctors/{id}/documents")
    public ResponseEntity<DocumentReference> uploadDoctorDocument(
            @PathVariable("id") String userId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") DocumentType type
    ) {
        log.info("Uploading document for doctor with id: {}", userId);
        return ResponseEntity.ok().body(userService.uploadDoctorDocument(userId, file, type));
    }
}



