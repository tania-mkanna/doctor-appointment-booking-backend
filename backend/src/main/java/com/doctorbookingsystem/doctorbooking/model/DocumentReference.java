package com.doctorbookingsystem.doctorbooking.model;

import com.doctorbookingsystem.doctorbooking.enums.DocumentStatus;
import com.doctorbookingsystem.doctorbooking.enums.DocumentType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentReference {
    /**
     * Type of the document (e.g., "ID_PROOF", "CERTIFICATE", etc.)
     */
    @Field("type")
    private DocumentType type;

    /**
     * URL or path where the document is stored
     */
    @Field("url")
    private String url;

    /**
     * Status of the document verification (e.g., "PENDING", "APPROVED", "REJECTED")
     */
    @Field("status")
    private DocumentStatus status;
}
