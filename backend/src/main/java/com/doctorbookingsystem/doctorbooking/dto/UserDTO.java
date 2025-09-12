package com.doctorbookingsystem.doctorbooking.dto;

import com.doctorbookingsystem.doctorbooking.enums.Role;
import com.doctorbookingsystem.doctorbooking.model.Doctor;
import com.doctorbookingsystem.doctorbooking.model.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object for User entity.
 */
@Data
public class UserDTO {

    private String id;

    @NotNull(message = "Username is required")
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 1, max = 255, message = "Username must be between 1 and 255 characters")
    private String userName;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String password;

    private String phoneNumber;

    @NotNull(message = "Role is required")
    private Role role;

    private String avatarUrl;

    private Doctor doctor;

    private Patient patient;
}
