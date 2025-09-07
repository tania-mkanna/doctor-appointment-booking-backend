package com.doctorbookingsystem.doctorbooking.repository;

import com.doctorbookingsystem.doctorbooking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
