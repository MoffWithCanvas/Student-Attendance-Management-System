package com.codecanvas.student_attendance_management_system.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SchoolClassDetailsRequest(
        @NotBlank(message = "Class name required")
        @Size(max = 10)
        String className,

        @NotBlank(message = "Form level is required")
        @Size(max = 10)
        String formLevel,

        @NotBlank(message = "Academic year is required")
        @Pattern(regexp = "^\\d{4}/\\d{4}$", message = "Format must be YYYY/YYYY")
        String academicYear
) {
}
