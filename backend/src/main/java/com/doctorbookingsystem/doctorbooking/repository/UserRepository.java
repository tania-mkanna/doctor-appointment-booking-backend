package com.doctorbookingsystem.doctorbooking.repository;

import com.doctorbookingsystem.doctorbooking.enums.Role;
import com.doctorbookingsystem.doctorbooking.model.User;
import com.mongodb.client.model.geojson.Point;

import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    // search doctors by full name (case insensitive)
    List<User> findByRoleAndDoctorFullNameIgnoreCaseContaining(Role role ,String fullName);
    // search doctors by specialty (case insensitive)
    @Query("{ 'role': ?0, 'doctor.specialties.name': { $regex: ?1, $options: 'i' } }")
    List<User> findDoctorsByRoleAndSpecialties(Role role ,String specialtyName);
    // search doctors by city (case insensitive)
    List<User> findByRoleAndDoctorCityIgnoreCaseContaining(Role role ,String city);


    // find neerby doctors within a certain distance from a given location
    List<User> findByRoleAndDoctorClinicLocationNear(Role role, GeoJsonPoint location, Distance distance);     
}
