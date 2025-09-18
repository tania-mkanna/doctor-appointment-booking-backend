package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.dto.DocumentReferenceDto;
import com.doctorbookingsystem.doctorbooking.dto.UserDTO;
import com.doctorbookingsystem.doctorbooking.enums.DocumentStatus;
import com.doctorbookingsystem.doctorbooking.enums.DocumentType;
import com.doctorbookingsystem.doctorbooking.exception.InvalidRequestException;
import com.doctorbookingsystem.doctorbooking.exception.NotFoundException;
import com.doctorbookingsystem.doctorbooking.mapper.UserMapper;
import com.doctorbookingsystem.doctorbooking.model.Doctor;
import com.doctorbookingsystem.doctorbooking.model.DocumentReference;
import com.doctorbookingsystem.doctorbooking.model.User;
import com.doctorbookingsystem.doctorbooking.repository.UserRepository;
import com.doctorbookingsystem.doctorbooking.service.interfaces.FileStorageService;
import com.doctorbookingsystem.doctorbooking.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FileStorageService fileStorageService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, FileStorageService fileStorageService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public Optional<UserDTO> findUserById(String userId) {
        log.info("Finding user with id: {}", userId);
        if (userId == null || userId.isBlank()) {
            throw new InvalidRequestException("User ID must be provided.");
        }
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found with ID: " + userId);
        }

        return Optional.ofNullable(userMapper.toDto(optionalUser.get()));
    }

    public DocumentReference uploadDoctorDocument(String userId, MultipartFile file, DocumentType documentType) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        Doctor doctor = user.getDoctor();
        if (doctor == null) {
            throw new InvalidRequestException("User with ID: " + userId + " is not a doctor.");
        }


        String path = null;
        try {
            path = fileStorageService.saveFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
            // todo: handle exception properly
        }

        DocumentReference reference = DocumentReference.builder().type(documentType).url(path).status(DocumentStatus.PENDING).build();
        if (doctor.getDocuments() == null) {
            doctor.setDocuments(new java.util.ArrayList<>());
        }
        doctor.getDocuments().add(reference);
        userRepository.save(user);
        log.info("Document of type {} uploaded for doctor with ID: {}", documentType, userId);

        return reference;


    }

    public UserDTO getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        return userMapper.toDto(user) ;
    }


}
