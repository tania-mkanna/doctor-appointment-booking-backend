package com.doctorbookingsystem.doctorbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class DoctorbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorbookingApplication.class, args);
	}

}
