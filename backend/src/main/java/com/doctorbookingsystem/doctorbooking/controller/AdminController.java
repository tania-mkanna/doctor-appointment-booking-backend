package com.doctorbookingsystem.doctorbooking.controller;

import com.doctorbookingsystem.doctorbooking.model.User;
import com.doctorbookingsystem.doctorbooking.service.interfaces.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

//    doctors with verificationStatus=PENDING
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pending-verificationStatus")
    public ResponseEntity<List<User>>getPendingVerificationStatus(){
        List<User> pendingVerificationStatus=adminService.getPendingVerificationStatus();
        return ResponseEntity.ok(pendingVerificationStatus);
    }

//    admin verify verificationStatus
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/verificationStatus/verify/{doctorId}")
    public ResponseEntity<User> verifyDoctor(@PathVariable String doctorId){
        User doctor=adminService.verifyDoctor(doctorId);
        return ResponseEntity.ok(doctor);
    }
//admin reject verificationStatus
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/verificationStatus/reject/{doctorId}")
    public ResponseEntity<User> rejectDoctor(@PathVariable String doctorId){
        User doctor=adminService.rejectDoctor(doctorId);
        return ResponseEntity.ok(doctor);
    }

//   doctors with DocumentStatus=PENDING
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pending-documentReference")
    public ResponseEntity<List<User>>getPendingDocumentReferenceStatus(){
        List<User>pendingDocumentStatus=adminService.getPendingDocumentReferenceStatus();
        return ResponseEntity.ok(pendingDocumentStatus);
    }

//    approve doctr's document reference
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/documentReference/approve/{doctorId}")

    public ResponseEntity<User>approveDocumentReference(@PathVariable String doctorId){
        User doctor=adminService.approveDocumentReference(doctorId);
        return ResponseEntity.ok(doctor);
    }

//    reject doctr's document reference
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/documentReference/reject/{doctorId}")

    public ResponseEntity<User>rejectDocumentReference(@PathVariable String doctorId){
        User doctor=adminService.rejectDocumentReference(doctorId);
        return ResponseEntity.ok(doctor);
    }


}
