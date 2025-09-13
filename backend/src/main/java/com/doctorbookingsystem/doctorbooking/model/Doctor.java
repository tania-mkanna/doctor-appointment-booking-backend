package com.doctorbookingsystem.doctorbooking.model;


import com.doctorbookingsystem.doctorbooking.enums.Gender;
import com.doctorbookingsystem.doctorbooking.enums.VerificationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.util.List;

/**
 * Doctor entity representing a medical professional in the system.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    /**
     * List of medical specialties the doctor is proficient in.
     */
    private List<Speciality> specialties;

    /**
     * The full name of the doctor.
     */
    @NotNull
    private String fullName;

    /**
     *
     */
    private Gender gender;

    /**
     * List of languages the doctor can communicate in.
     */
    private List<String> languages;

    /**
     * Years of experience the doctor has in their medical practice.
     */
    private int yearsOfExperience;

    /**
     * Brief biography or description of the doctor's background and expertise.
     */
    private String bio;

    /**
     * Clinic address where the doctor practices.
     */
    //MongoDB always expects [lng, lat] order, not [lat, lng]
    @GeoSpatialIndexed
    private GeoJsonPoint clinicLocation; // new GeoJsonPoint(lng, lat)

    /**
     * City where the doctor is located.
     */
    private String city;

    /**
     * price range for consultations or services offered by the doctor.
     */
    private List<ServicePrice> services;

    /**
     * Verification status of the doctor's credentials and documents.(he must submit his documents)
     */
    private VerificationStatus verificationStatus;

    /**
     * List of document references associated with the doctor (e.g., medical license, certifications).
     */
    private List<DocumentReference> documents;

    /**
     * Average rating given by patients based on their experiences with the doctor.
     */
    private Double avgRating;

    /**
     * Total number of reviews received by the doctor from patients.
     */
    private Integer reviewsCount;

}
