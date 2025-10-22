package com.doctorbookingsystem.doctorbooking.repository;

import com.doctorbookingsystem.doctorbooking.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {


//    doctors review
    List<Review> findByDoctorId();

//    reviews written by patients
    List<Review> findByPatientId();

//    find reviews by appointment

    Review findByAppointmentId();

    List<Review> findByAppointmentIdAndPatientId(String appointmentId,String patientId);

}
