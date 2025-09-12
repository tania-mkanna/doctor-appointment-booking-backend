package com.doctorbookingsystem.doctorbooking.model;



import com.doctorbookingsystem.doctorbooking.enums.AppointmentStatus;
import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Document(collection = "appointment")
public class Appointment extends Audit {

    @Id
    private String id;

    @Indexed
    @Field("patientId")
    private String patientId;

    @Indexed
    @Field("doctorId")
    private String doctorId;

    @Field("slotId")
    private String slotId;

    @Indexed
    @Field("start")
    private Instant start;

    @Field("end")
    private Instant end;

    @Indexed
    @Field("status")
    private AppointmentStatus status;

    @Field("reason")
    private String reason;

    @Field("notes")
    private String notes;
}
