package com.doctorbookingsystem.doctorbooking.service.interfaces;


import com.doctorbookingsystem.doctorbooking.dto.UserDTO;

import java.util.Optional;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {

    Optional<UserDTO> findUserById(String userId);
}

