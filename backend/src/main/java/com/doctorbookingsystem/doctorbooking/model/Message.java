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
@Document(collection = "message")
public class Message extends Audit {

    @Id
    private String id;

    @Indexed
    @Field("appointmentId")
    private String appointmentId;

    @Indexed
    @Field("fromUserId")
    private String fromUserId;

    @Indexed
    @Field("toUserId")
    private String toUserId;

    @Field("content")
    private String content;
}
