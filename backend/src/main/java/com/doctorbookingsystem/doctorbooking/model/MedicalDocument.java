package com.doctorbookingsystem.doctorbooking.model;

import com.doctorbookingsystem.doctorbooking.enums.MedicalDocumentType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "medicalDocument")
public class MedicalDocument extends Audit{

    @Id
    private String id;

    @Field("patientId")
    private String patientId;

    @Field("doctorId")
    private String doctorId;


    @Field("type")
    private MedicalDocumentType type;

    @Field("fileUrl")
    private String fileUrl;

    @Field("documentCreatedAt")
    private Instant createdAt;
}
