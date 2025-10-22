package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.model.Review;
import com.doctorbookingsystem.doctorbooking.repository.ReviewRepository;
import com.doctorbookingsystem.doctorbooking.repository.UserRepository;
import com.doctorbookingsystem.doctorbooking.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,UserRepository userRepository){
        this.reviewRepository=reviewRepository;
        this.userRepository=userRepository;
    }

//    get doctor's review
    public List<Review> getDoctorReviews(String doctorId){
        return reviewRepository.findByDoctorId();
    }

    //    get doctor's review
    public List<Review> getReviewsByPatient(String patientId){
        return reviewRepository.findByPatientId();
    }

//    add review
    public Review addReview(Review review){

    }




}
