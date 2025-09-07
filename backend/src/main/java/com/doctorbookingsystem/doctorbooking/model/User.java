package com.doctorbookingsystem.doctorbooking.model;


import com.doctorbookingsystem.doctorbooking.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User extends Audit{

    @Id
    private String id;

    @Indexed(unique = true)
    @Field("email")
    private String email;

    @Field("passwordHash")
    private String passwordHash;

    @Field("role")
    private Role role;

    @Field("fullName")
    private String fullName;

    @Field("phone")
    private String phone;

    @Field("avatarUrl")
    private String avatarUrl;

}
