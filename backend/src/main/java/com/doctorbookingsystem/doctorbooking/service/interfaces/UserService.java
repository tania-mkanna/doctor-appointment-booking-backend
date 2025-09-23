package com.doctorbookingsystem.doctorbooking.service.interfaces;


import com.doctorbookingsystem.doctorbooking.dto.DoctorPatientViewDTO;
import com.doctorbookingsystem.doctorbooking.dto.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {

    Optional<UserDTO> findUserById(String userId);
    List<DoctorPatientViewDTO> searchDoctors(String text);
    List<DoctorPatientViewDTO> findNearbyDoctors(double latitude, double longitude);
}

