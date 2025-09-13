package com.doctorbookingsystem.doctorbooking.model;



import com.doctorbookingsystem.doctorbooking.enums.AppointmentPriority;
import com.doctorbookingsystem.doctorbooking.enums.AppointmentStatus;
import com.doctorbookingsystem.doctorbooking.enums.CaseType;
import lombok.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Document(collection = "appointments")
public class Appointment extends Audit {

    /**
     * The unique identifier for the appointment.
     */
    @Id
    private String id;

    private User doctor;

    private User patient;

    /**
     * The slot ID for the appointment.
     */
    @DBRef
    private AvailabilitySlot slot;

    /**
     * The status of the appointment.
     */
    @Indexed
    @Field("status")
    private AppointmentStatus status;

    /**
     * The reason for the appointment.
     */
    @Field("reason")
    private CaseType caseType;

    /**
     * Additional notes for the appointment.
     */
    @Field("notes")
    private String notes;

    /**
     * The priority of the appointment.
     */
    @Field("priority")
    AppointmentPriority priority; //assigned by the doctor
}
