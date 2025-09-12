package com.doctorbookingsystem.doctorbooking.model;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceRange {

    @Field("currency")
    private String currency; // e.g. "USD"
    @Field("min")
    private Double min;
    @Field("max")
    private Double max;

}
