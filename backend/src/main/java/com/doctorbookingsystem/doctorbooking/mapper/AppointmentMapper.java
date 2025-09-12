package com.doctorbookingsystem.doctorbooking.mapper;

import com.doctorbookingsystem.doctorbooking.dto.AppointmentDTO;
import com.doctorbookingsystem.doctorbooking.model.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    Appointment toEntity(AppointmentDTO dto);
    AppointmentDTO toDto(Appointment entity);
}
