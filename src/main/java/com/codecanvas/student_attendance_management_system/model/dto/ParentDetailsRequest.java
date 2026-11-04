package com.codecanvas.student_attendance_management_system.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ParentDetailsRequest(
        @NotBlank(message = "First name cannot be empty")
        @Size(max = 20)
        String firstName,

        @NotBlank(message = "Last name cannot be empty")
        @Size(max = 20)
        String lastName,

        @NotBlank(message = "Phone number required")
        @Size(max = 12)
        String phoneNumber,

        @NotBlank(message = "Email cannot be empty")
        @Email
        String email,

        @NotBlank(message = "Address cannot be empty")
        @Size(max = 100)
        String physicalAddress
) {
}
