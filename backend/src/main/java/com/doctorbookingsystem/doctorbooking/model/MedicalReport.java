package com.doctorbookingsystem.doctorbooking.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalReport {

    private String title;
    private String description;
    private String reportType;
}
