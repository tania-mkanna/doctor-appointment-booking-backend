package com.doctorbookingsystem.doctorbooking.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "availabilitySlot")
public class AvailabilitySlot  extends Audit {

    @Id
    private String id;

    @Indexed
    @Field("doctorId")
    private String doctorId;

    @Indexed
    @Field("start")
    private Instant start;

    @Field("end")
    private Instant end;

    @Indexed
    @Field("isBooked")
    private boolean isBooked;






}
