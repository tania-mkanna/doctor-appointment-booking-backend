package com.doctorbookingsystem.doctorbooking.model;


import com.doctorbookingsystem.doctorbooking.enums.VerificationStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "doctorProfile")
public class DoctorProfile {

    @Id
    private String id; // same as doctor Id

    @Indexed
    @Field("specialties")
    private List<String> specialties;

    @Field("languages")
    private List<String> languages;

    @Field("yearsOfExperience")
    private Integer yearsOfExperience;

    @Field("bio")
    private String bio;

    //MongoDB always expects [lng, lat] order, not [lat, lng]
    @GeoSpatialIndexed
    @Field("clinicLocation")
    private GeoJsonPoint clinicLocation; // new GeoJsonPoint(lng, lat)

    @Field("city")
    private String city;

    @Field("priceRange")
    private PriceRange priceRange;

    @Field("verificationStatus")
    private VerificationStatus verificationStatus;

    @Field("documents")
    private List<DocumentRef> documents;

    @Indexed
    @Field("avgRating")
    private Double avgRating;

    @Field("reviewsCount")
    private Integer reviewsCount;
}
