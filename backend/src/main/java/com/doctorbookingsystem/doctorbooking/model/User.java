package com.doctorbookingsystem.doctorbooking.model;


import com.doctorbookingsystem.doctorbooking.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents a user in the system.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User extends Audit{

    @Id
    private String id;

    @Field("userName")
    private String userName;

    @Indexed(unique = true)
    @Field("email")
    private String email;

    @Field("password")
    private String password;

    @Field("phoneNumber")
    private String phoneNumber;

    @Field("role")
    private Role role;

    @Field("avatarUrl")
    private String avatarUrl;

    private Doctor doctor;
    private Patient patient;

}
