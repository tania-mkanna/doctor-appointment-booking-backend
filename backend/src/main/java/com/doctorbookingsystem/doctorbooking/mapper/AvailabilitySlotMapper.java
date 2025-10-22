package com.doctorbookingsystem.doctorbooking.mapper;

import com.doctorbookingsystem.doctorbooking.dto.AvailabilitySlotDTO;
import com.doctorbookingsystem.doctorbooking.model.AvailabilitySlot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvailabilitySlotMapper {

    AvailabilitySlotDTO toDto(AvailabilitySlot entity);
    AvailabilitySlot toEntity(AvailabilitySlotDTO dto);
}
