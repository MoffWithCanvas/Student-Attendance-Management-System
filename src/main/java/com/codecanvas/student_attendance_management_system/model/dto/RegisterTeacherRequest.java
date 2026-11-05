package com.codecanvas.student_attendance_management_system.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterTeacherRequest(
        @NotNull
        @Valid
        UserDetailsRequest user,

        @NotBlank(message = "Department is required")
        @Size(max = 100)
        String department,

        @NotNull(message = "subject is required")
        Long subjectId
) {
}
