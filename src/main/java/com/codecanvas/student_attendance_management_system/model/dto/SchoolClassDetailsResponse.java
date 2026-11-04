package com.codecanvas.student_attendance_management_system.model.dto;

import com.codecanvas.student_attendance_management_system.model.entity.SchoolClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SchoolClassDetailsResponse(
        Long id,
        String className,
        String formLevel,
        String academicYear
) {
    public static SchoolClassDetailsResponse from(SchoolClass schoolClass){
        return new SchoolClassDetailsResponse(
                schoolClass.getId(),
                schoolClass.getClassName(),
                schoolClass.getFormLevel(),
                schoolClass.getAcademicYear());
    }
}
