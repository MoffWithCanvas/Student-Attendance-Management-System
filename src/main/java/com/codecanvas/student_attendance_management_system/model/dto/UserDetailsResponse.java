package com.codecanvas.student_attendance_management_system.model.dto;

import com.codecanvas.student_attendance_management_system.model.entity.Role;
import com.codecanvas.student_attendance_management_system.model.entity.User;

import java.time.LocalDate;

public record UserDetailsResponse(
        Long id,
        String firstName,
        String lastName,
        String username,
        String address,
        LocalDate dateOfBirth,
        String email,
        Role role
) {
    public static UserDetailsResponse from(User user){
        return new UserDetailsResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getAddress(),
                user.getDateOfBirth(),
                user.getEmail(),
                user.getRole());
    }
}
