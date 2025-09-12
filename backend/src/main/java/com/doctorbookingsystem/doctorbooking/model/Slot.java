package com.doctorbookingsystem.doctorbooking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "slots")
public class Slot {
    @Id
    private String id;
    private String doctorId;
    private Instant start;
    private Instant end;
    private boolean isBooked;
}