package com.doctorbookingsystem.doctorbooking.service.interfaces;


import com.doctorbookingsystem.doctorbooking.dto.DocumentReferenceDto;
import com.doctorbookingsystem.doctorbooking.dto.UserDTO;
import com.doctorbookingsystem.doctorbooking.enums.DocumentType;
import com.doctorbookingsystem.doctorbooking.model.DocumentReference;
import com.doctorbookingsystem.doctorbooking.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {

    Optional<UserDTO> findUserById(String userId);

    DocumentReference uploadDoctorDocument(String userId, MultipartFile file, DocumentType documentType);

    UserDTO getUserById(String id);
}

