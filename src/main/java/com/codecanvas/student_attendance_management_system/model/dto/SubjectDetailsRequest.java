package com.codecanvas.student_attendance_management_system.model.dto;

import com.codecanvas.student_attendance_management_system.model.entity.SubjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*
   Validated UserDetailsRequest DTO that helps to reject bad
   client input early and returns friendly error messages (HTTP 400)

 */
public record SubjectDetailsRequest(
        @NotBlank(message = "Subject code is required")
        @Size(max = 255)
        @Pattern(regexp = "^[A-Z0-9-]+$")
        String subjectCode,

        @NotBlank(message = "Subject name cannot be blank")
        @Size(max = 20)
        String subjectName,

        @NotNull(message = "Credits required")
        Integer credits,

        @NotNull(message = "Subject type required")
        SubjectType subjectType
) {
}
