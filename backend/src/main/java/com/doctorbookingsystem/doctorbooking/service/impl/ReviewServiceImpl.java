package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.enums.AppointmentStatus;
import com.doctorbookingsystem.doctorbooking.model.Appointment;
import com.doctorbookingsystem.doctorbooking.model.Doctor;
import com.doctorbookingsystem.doctorbooking.model.Review;
import com.doctorbookingsystem.doctorbooking.model.User;
import com.doctorbookingsystem.doctorbooking.repository.AppointmentRepository;
import com.doctorbookingsystem.doctorbooking.repository.ReviewRepository;
import com.doctorbookingsystem.doctorbooking.repository.UserRepository;
import com.doctorbookingsystem.doctorbooking.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,UserRepository userRepository, AppointmentRepository appointmentRepository){
        this.reviewRepository=reviewRepository;
        this.userRepository=userRepository;
        this.appointmentRepository=appointmentRepository;
    }

//    get doctor's review
    public List<Review> getDoctorReviews(String doctorId){
        return reviewRepository.findByDoctorId(doctorId);
    }

    //    get reviews written by patient
    public List<Review> getReviewsByPatient(String patientId){
        return reviewRepository.findByPatientId(patientId);
    }

//    update doctor's rating
    private void updateDoctorRating(String doctorId){


        List<Review> reviews=reviewRepository.findByDoctorId(doctorId);
        int totalReviews=reviews.size();

        double avgRating=reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        User doctor=userRepository.findById(doctorId)
                        .orElseThrow(()->new RuntimeException("doctor not fount"));



        doctor.getDoctor().setAvgRating(avgRating);
        doctor.getDoctor().setReviewsCount(totalReviews);
        userRepository.save(doctor);

    }


//    add review
    public Review addReview(Review review){
        Appointment appointment = appointmentRepository.findById(review.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if(appointment.getStatus() != AppointmentStatus.COMPLETED){
            throw new RuntimeException("Appointment must be completed");


        }
        Review existingReview=reviewRepository.findByAppointmentIdAndPatientId(review.getAppointmentId(),review.getPatientId());
        if(existingReview != null){
            throw new RuntimeException("review already submitted");
        }

        Review savedReview =reviewRepository.save(review);
        updateDoctorRating(review.getDoctorId());

        return savedReview;
    }




}
