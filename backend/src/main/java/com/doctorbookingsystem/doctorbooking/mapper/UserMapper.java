package com.doctorbookingsystem.doctorbooking.mapper;

import com.doctorbookingsystem.doctorbooking.dto.UserDTO;
import com.doctorbookingsystem.doctorbooking.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);

}