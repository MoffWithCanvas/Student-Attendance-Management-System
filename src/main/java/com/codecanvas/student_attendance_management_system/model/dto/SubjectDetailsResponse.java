package com.codecanvas.student_attendance_management_system.model.dto;

import com.codecanvas.student_attendance_management_system.model.entity.Subject;
import com.codecanvas.student_attendance_management_system.model.entity.SubjectType;

public record SubjectDetailsResponse(
        Long id,
        String subjectCode,
        String subjectName,
        int credits,
        SubjectType subjectType
) {
    // A static from() method used to convert an entity into a response DTO
    public static SubjectDetailsResponse from(Subject subject){
        return new SubjectDetailsResponse(
                subject.getId(),
                subject.getSubjectCode(),
                subject.getSubjectName(),
                subject.getCredits(),
                subject.getSubjectType());
    }
}
