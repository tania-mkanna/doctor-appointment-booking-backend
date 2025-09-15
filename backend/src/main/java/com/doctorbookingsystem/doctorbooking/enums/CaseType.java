package com.doctorbookingsystem.doctorbooking.enums;

public enum CaseType {
    /**
     * A routine visit to assess overall health.
     */
    GENERAL_CHECKUP,

    /**
     * A follow-up appointment after a diagnosis or ongoing treatment.
     */
    FOLLOW_UP,

    /**
     * Appointment for flu-like symptoms or viral infections.
     * Example: fever, cough, sore throat, body aches.
     */
    FLU,

    /**
     * Appointment related to physical trauma or injuries.
     */
    INJURY,

    /**
     * Appointment to perform or review laboratory blood tests.
     */
    BLOOD_TEST,

    /**
     * Consultation with a surgeon to discuss potential or scheduled surgery.
     */
    SURGERY_CONSULTATION,

    /**
     * Appointment related to dental or oral health.
     * Example: cavity filling, tooth extraction, cleaning, orthodontic check.
     */
    DENTAL,

    /**
     * A general fallback for cases not covered by other categories.
     * Example: skin rash, mental health consultation, vaccination.
     */
    OTHER
}

