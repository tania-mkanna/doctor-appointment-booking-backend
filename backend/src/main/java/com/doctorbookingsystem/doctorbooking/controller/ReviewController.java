package com.doctorbookingsystem.doctorbooking.controller;

import com.doctorbookingsystem.doctorbooking.model.Review;
import com.doctorbookingsystem.doctorbooking.service.interfaces.ReviewService;
import com.doctorbookingsystem.doctorbooking.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/reviews")
@RestController
public class ReviewController {

    ReviewService reviewService;
//    UserService userService;
    public ReviewController(ReviewService reviewService){
        this.reviewService=reviewService;

    }

//    get doctor's reviews
    @GetMapping("/doctors/{doctorId}")
    ResponseEntity <List<Review>>getDoctorReviews(@PathVariable String doctorId){

        List <Review> reviews=reviewService.getDoctorReviews(doctorId);
        return ResponseEntity.ok(reviews);
    }

//    get reviews written by patient
    @GetMapping("/patient/{patientId}")
    ResponseEntity<List<Review>> getReviewsByPatient(@PathVariable String patientId){
        List<Review> reviews=reviewService.getReviewsByPatient(patientId);
        return ResponseEntity.ok(reviews);
    }

//    get reviews by id

//create review
    @PostMapping
    ResponseEntity<Review>addReview(@RequestBody Review review){
        Review savedReview =reviewService.addReview(review);
        return ResponseEntity.ok(savedReview);
    }
}
