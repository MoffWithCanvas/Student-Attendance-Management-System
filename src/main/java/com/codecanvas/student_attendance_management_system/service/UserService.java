package com.codecanvas.student_attendance_management_system.service;

import com.codecanvas.student_attendance_management_system.model.dto.UserDetailsRequest;
import com.codecanvas.student_attendance_management_system.model.dto.UserDetailsResponse;
import com.codecanvas.student_attendance_management_system.model.entity.Role;
import com.codecanvas.student_attendance_management_system.model.entity.User;
import com.codecanvas.student_attendance_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(UserDetailsRequest request, Role role){
        //Check if email and username already exists
        if (userRepository.existsByEmail(request.email().trim().toLowerCase())){
            throw new IllegalStateException("Email already exists");
        }
        if (userRepository.existsByUsername(request.username().trim().toLowerCase())){
            throw new IllegalStateException("Username already exists");
        }
        //Creating a user using the static factory method in User.java
        User user = User.create(
                request.firstName(),
                request.lastName(),
                request.username(),
                request.address(),
                request.dateOfBirth(),
                request.email(),
                passwordEncoder.encode(request.password()),
                role
        );
        return userRepository.save(user);
    }
}