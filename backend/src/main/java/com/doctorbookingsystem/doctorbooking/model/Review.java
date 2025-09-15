package com.doctorbookingsystem.doctorbooking.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "reviews")
public class Review extends Audit{

    @Id
    private String id;

    @Indexed
    @Field("appointmentId")
    private String appointmentId;

    @Indexed
    @Field("patientId")
    private String patientId;

    @Indexed
    @Field("doctorId")
    private String doctorId;

    @Field("rating")
    private Integer rating; // 1..5

    @Field("comment")
    private String comment;

}
