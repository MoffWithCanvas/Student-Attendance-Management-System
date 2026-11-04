package com.codecanvas.student_attendance_management_system.model.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
/*
   Validated UserDetailsRequest DTO that helps to reject bad
   client input early and returns friendly error messages (HTTP 400)
 */
public record UserDetailsRequest(
        @NotBlank(message = "First name cannot be blank")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Username cannot be blank")
        @Pattern(regexp = "^[a-zA-Z0-9._]+$", message = "Only letters, digits, dots and underscores")
        String username,

        @NotBlank(message = "Address cannot be blank")
        @Size(max = 255)
        String address,

        @NotNull(message = "Date of birth cannot be blank")
        @Past
        LocalDate dateOfBirth,

        @NotBlank(message = "Email cannot be blank")
        @Email
        String email,

        @NotBlank(message = "Cannot be blank")
        @Size(min = 8)
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*]).+$",
        message = "Must include upper, lower, digit and special character")
        String password
) { }
