package com.doctorbookingsystem.doctorbooking.repository;

import com.doctorbookingsystem.doctorbooking.model.DoctorProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorProfileRepository extends MongoRepository<DoctorProfile, String> {

}
