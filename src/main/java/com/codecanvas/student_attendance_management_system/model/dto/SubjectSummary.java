package com.codecanvas.student_attendance_management_system.model.dto;

// Small subject summary record instead of the full subject response
public record SubjectSummary(
        Long id,
        String subjectCode,
        String subjectName
) {}
