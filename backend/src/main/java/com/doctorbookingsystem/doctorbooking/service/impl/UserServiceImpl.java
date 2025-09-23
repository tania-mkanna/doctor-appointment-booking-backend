package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.dto.DoctorPatientViewDTO;
import com.doctorbookingsystem.doctorbooking.dto.UserDTO;
import com.doctorbookingsystem.doctorbooking.enums.Role;
import com.doctorbookingsystem.doctorbooking.exception.InvalidRequestException;
import com.doctorbookingsystem.doctorbooking.exception.NotFoundException;
import com.doctorbookingsystem.doctorbooking.mapper.DoctorPatientViewMapper;
import com.doctorbookingsystem.doctorbooking.mapper.UserMapper;
import com.doctorbookingsystem.doctorbooking.model.Doctor;
import com.doctorbookingsystem.doctorbooking.model.Patient;
import com.doctorbookingsystem.doctorbooking.model.User;
import com.doctorbookingsystem.doctorbooking.repository.UserRepository;
import com.doctorbookingsystem.doctorbooking.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final DoctorPatientViewMapper doctorPatientViewMapper;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, DoctorPatientViewMapper doctorPatientViewMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;

        this.doctorPatientViewMapper = doctorPatientViewMapper;
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

//    doctor's profile
    public UserDTO getDoctorProfile(String doctorId){
        UserDTO userDTO =findUserById(doctorId)
                .orElseThrow(()->new RuntimeException("doctor not found"));

        if(userDTO.getRole() != Role.DOCTOR ){
            throw new RuntimeException("the user is not Doctor");
        }


        return userDTO;

    }

//    Patient profile
    public UserDTO getPatientProfile(String patientId){
        UserDTO userDTO =findUserById(patientId)
                .orElseThrow(()-> new RuntimeException("Patient is not exits"));

        if(userDTO.getRole() != Role.PATIENT ){
            throw new RuntimeException("the user is not Patient");
        }

        return userDTO;

    }

    // implement searchDoctors method

    public List<DoctorPatientViewDTO> searchDoctors(String text) {

        log.info("Searching doctors with text: {}", text);

        if(text == null || text.isBlank()) {
            return List.of();
        }
        // search by name, specialty, city
        List<User> doctorsByName = userRepository.findByRoleAndDoctorFullNameIgnoreCaseContaining(Role.DOCTOR, text);
        List<User> doctorsBySpecialty = userRepository.findDoctorsByRoleAndSpecialties(Role.DOCTOR, text);
        List<User> doctorsByCity = userRepository.findByRoleAndDoctorCityIgnoreCaseContaining(Role.DOCTOR, text);

        // combine results
        List<User> combinedDoctors = new ArrayList<>();
        combinedDoctors.addAll(doctorsByName);
        combinedDoctors.addAll(doctorsBySpecialty);
        combinedDoctors.addAll(doctorsByCity);

        // remove duplicates
        List<DoctorPatientViewDTO> uniqueDoctors = combinedDoctors.stream()
                .distinct()
                .map(doctorPatientViewMapper::toDto)
                .collect(Collectors.toList());

        return uniqueDoctors;


    }

    // implement findNearbyDoctors method
    public List<DoctorPatientViewDTO> findNearbyDoctors(double latitude, double longitude) {
        log.info("Finding nearby doctors for location: ({}, {})", latitude, longitude);

        // validate latitude and longitude
        // latitude must be between -90 and 90 (north and south poles)
        if(latitude < -90 || latitude > 90) {
            throw new InvalidRequestException("Invalid latitude value. It must be between -90 and 90.");
        }
        // longitude must be between -180 and 180 (east and west)
        if(longitude < -180 || longitude > 180) {
            throw new InvalidRequestException("Invalid longitude value. It must be between -180 and 180.");
        }
        // create GeoJsonPoint for the given location
        GeoJsonPoint location = new GeoJsonPoint(longitude, latitude);

        // define a distance of 5 km
        Distance maxDistance= new Distance(5,Metrics.KILOMETERS);

        // find nearby doctors
        List<User> nearbyDoctors = userRepository.findByRoleAndDoctorClinicLocationNear(Role.DOCTOR, location, maxDistance);

        // map to DTOs
        return nearbyDoctors.stream()
                .map(doctorPatientViewMapper::toDto)
                .collect(Collectors.toList());
    }

}
