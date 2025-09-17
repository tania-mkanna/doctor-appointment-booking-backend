package com.doctorbookingsystem.doctorbooking.repository;

import com.doctorbookingsystem.doctorbooking.model.AvailabilitySlot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AvailabilitySlotRepository extends MongoRepository<AvailabilitySlot, String> {

    List<AvailabilitySlot> findByDoctorId(String doctorId);
    List<AvailabilitySlot> findByDoctorIdAndIsBookedFalse(String doctorId);


}
