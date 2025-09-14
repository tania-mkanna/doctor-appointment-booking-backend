package com.doctorbookingsystem.doctorbooking.dto;

import java.util.List;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.doctorbookingsystem.doctorbooking.model.Speciality;

import lombok.Data;

@Data
public class DoctorPatientViewDTO {
    private String fullName;
    private Double avgRating;
    private Integer reviewsCount;
    private List<Speciality> specialties;
    private int yearsOfExperience;
    private GeoJsonPoint clinicLocation;
    private List<String> languages;
    
}

