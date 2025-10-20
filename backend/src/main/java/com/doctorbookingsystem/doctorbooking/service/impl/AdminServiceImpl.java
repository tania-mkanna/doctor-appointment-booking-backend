package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.enums.DocumentStatus;
import com.doctorbookingsystem.doctorbooking.enums.Role;
import com.doctorbookingsystem.doctorbooking.enums.VerificationStatus;
import com.doctorbookingsystem.doctorbooking.model.User;
import com.doctorbookingsystem.doctorbooking.repository.UserRepository;
import com.doctorbookingsystem.doctorbooking.service.interfaces.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    public AdminServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    //    get doctors verification status (verificationStatus=PENDING)
    public List<User> getPendingVerificationStatus(){
        return userRepository.findByRoleAndDoctorVerificationStatus(Role.DOCTOR, VerificationStatus.PENDING);
    }

//    verify doctor
    public User verifyDoctor(String doctorId){
        User doctor=userRepository.findById(doctorId)
                .orElseThrow(()->new RuntimeException("Doctor not found"));
        if(doctor.getRole() != Role.DOCTOR){
            throw new RuntimeException("this user is not doctor");

        }
        doctor.getDoctor().setVerificationStatus(VerificationStatus.VERIFIED);

        return userRepository.save(doctor);
    }

//    reject doctor
    public User rejectDoctor(String doctorId){
        User doctor=userRepository.findById(doctorId)
                .orElseThrow(()->new RuntimeException("Doctor not found"));
        if(doctor.getRole() != Role.DOCTOR){
            throw new RuntimeException("this user is not doctor");

        }
        doctor.getDoctor().setVerificationStatus(VerificationStatus.REJECTED);
        return userRepository.save(doctor);
    }

    //    get doctors with pending document reference
    public List<User> getPendingDocumentReferenceStatus(){
        return userRepository.findByRoleAndDoctorDocuments(Role.DOCTOR, DocumentStatus.PENDING);
    }

//    approve doctor's documents
    public User approveDocumentReference(String doctorId){

        User doctor=userRepository.findById(doctorId)
                .orElseThrow(()->new RuntimeException("the user is not found"));

        if(doctor.getRole() != Role.DOCTOR){
            throw new RuntimeException("the user is not doctor");
        }
        if(doctor.getDoctor() !=null && doctor.getDoctor().getDocuments()!=null){
            doctor.getDoctor().getDocuments().forEach(document->document.setStatus(DocumentStatus.APPROVED));

        }

        return userRepository.save(doctor);
    }

    public User rejectDocumentReference(String doctorId){

        User doctor=userRepository.findById(doctorId)
                .orElseThrow(()->new RuntimeException("the user is not found"));

        if(doctor.getRole() != Role.DOCTOR){
            throw new RuntimeException("the user is not doctor");
        }
        if(doctor.getDoctor() !=null && doctor.getDoctor().getDocuments() != null){
            doctor.getDoctor().getDocuments().forEach(document->document.setStatus(DocumentStatus.REJECTED));

        }

        return userRepository.save(doctor);
    }
}
