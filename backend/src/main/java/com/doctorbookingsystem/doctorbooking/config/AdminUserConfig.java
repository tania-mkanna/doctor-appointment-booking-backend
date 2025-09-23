package com.doctorbookingsystem.doctorbooking.config;

import com.doctorbookingsystem.doctorbooking.enums.Role;
import com.doctorbookingsystem.doctorbooking.model.User;
import com.doctorbookingsystem.doctorbooking.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserConfig {
    @Bean
    public User adminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return userRepository.findByEmail("admin@gmail.com")
                .orElseGet(() -> {
                    User admin = new User();
                    admin.setEmail("admin@gmail.com");
                    admin.setPassword(passwordEncoder.encode("admin"));
                    admin.setRole(Role.ADMIN);
                    return userRepository.save(admin);
                });
    }
}
