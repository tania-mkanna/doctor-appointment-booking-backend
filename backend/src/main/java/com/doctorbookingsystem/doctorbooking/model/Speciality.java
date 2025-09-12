package com.doctorbookingsystem.doctorbooking.model;

import lombok.Data;

@Data
public class Speciality {
    private String name;

    /**
     * Indicates if this is a major(true) specialty or a subSpecialty(false)
     */
    private boolean isMajor;

    /**
     * Number of years of experience in this specialty
     */
    private int yearsExperience;
}