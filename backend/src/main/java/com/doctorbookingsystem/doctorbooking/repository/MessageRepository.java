package com.doctorbookingsystem.doctorbooking.repository;

import com.doctorbookingsystem.doctorbooking.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository  extends MongoRepository<Message, String> {

}
