package com.doctorbookingsystem.doctorbooking.mapper;

import com.doctorbookingsystem.doctorbooking.dto.DoctorPatientViewDTO;
import com.doctorbookingsystem.doctorbooking.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorPatientViewMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "avatarUrl", target = "avatarUrl")
    @Mapping(source = "doctor.fullName", target = "fullName")
    @Mapping(source = "doctor.avgRating", target = "avgRating")
    @Mapping(source = "doctor.reviewsCount", target = "reviewsCount")
    @Mapping(source = "doctor.specialties", target = "specialties")
    @Mapping(source = "doctor.yearsOfExperience", target = "yearsOfExperience")
    @Mapping(source = "doctor.clinicLocation", target = "clinicLocation")
    @Mapping(source = "doctor.languages", target = "languages")
    DoctorPatientViewDTO toDto(User doctorUser);
}
