package com.doctorbookingsystem.doctorbooking.controller;

import com.doctorbookingsystem.doctorbooking.service.interfaces.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing user-related operations.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }
    private final UserService userService;

}
