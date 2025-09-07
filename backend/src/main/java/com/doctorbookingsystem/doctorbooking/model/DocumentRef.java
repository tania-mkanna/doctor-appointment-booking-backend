package com.doctorbookingsystem.doctorbooking.model;

import com.doctorbookingsystem.doctorbooking.enums.DocumentType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentRef {
    @Field("type")
    private DocumentType type;

    @Field("url")
    private String url;

    @Field("status")
    private String status; // "PENDING" | "APPROVED" | "REJECTED"
}
