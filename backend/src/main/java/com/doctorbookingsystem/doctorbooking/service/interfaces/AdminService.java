package com.doctorbookingsystem.doctorbooking.service.interfaces;

import com.doctorbookingsystem.doctorbooking.model.User;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

//    get doctors verification status (verificationStatus=PENDING)
    public List<User> getPendingVerificationStatus();

//    verify doctor
    User verifyDoctor(String doctorId);

//    reject doctor
    User rejectDoctor(String doctorId);

//    get doctors reference document Status(status=PENDING)
    public List<User> getPendingDocumentReferenceStatus();

//    approve doctor's document reference
    User approveDocumentReference(String doctorId);

//    reject doctor's document reference
    User rejectDocumentReference(String doctorId);

}
