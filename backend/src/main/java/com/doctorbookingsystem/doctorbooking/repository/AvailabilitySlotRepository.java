package com.doctorbookingsystem.doctorbooking.repository;

import com.doctorbookingsystem.doctorbooking.model.AvailabilitySlot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvailabilitySlotRepository extends MongoRepository<AvailabilitySlot, String> {
}
