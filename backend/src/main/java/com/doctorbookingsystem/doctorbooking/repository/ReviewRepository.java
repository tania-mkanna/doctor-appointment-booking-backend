package com.doctorbookingsystem.doctorbooking.repository;

import com.doctorbookingsystem.doctorbooking.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {

}
