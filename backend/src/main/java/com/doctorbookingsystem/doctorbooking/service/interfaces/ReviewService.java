package com.doctorbookingsystem.doctorbooking.service.interfaces;

import com.doctorbookingsystem.doctorbooking.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

//    get doctor's reviews
    public List<Review> getDoctorReviews(String doctorId);

//    get reviews written by patient
    public List<Review> getReviewsByPatient(String patientId);

//add review
    public Review addReview(Review review);

}
