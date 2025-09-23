package com.doctorbookingsystem.doctorbooking.dto;

import com.doctorbookingsystem.doctorbooking.enums.Gender;
import com.doctorbookingsystem.doctorbooking.model.ServicePrice;
import com.doctorbookingsystem.doctorbooking.model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorRegisterRequest extends RegisterRequest{
    private String fullName;
    private Gender gender;
    private List<String> languages;
    private int yearsOfExperience;
    private String bio;
    private GeoJsonPoint clinicLocation;  // or a custom Lat/Lng DTO
    private String city;
    private List<ServicePrice> services;
    private List<Speciality> specialties;

}
