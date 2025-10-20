package com.doctorbookingsystem.doctorbooking.repository;

import com.doctorbookingsystem.doctorbooking.model.MedicalDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalDocumentRepository extends MongoRepository<MedicalDocument,String> {
}
