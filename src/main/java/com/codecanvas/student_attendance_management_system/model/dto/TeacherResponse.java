package com.codecanvas.student_attendance_management_system.model.dto;

import com.codecanvas.student_attendance_management_system.model.entity.Subject;
import com.codecanvas.student_attendance_management_system.model.entity.Teacher;

public record TeacherResponse(
        Long id,
        UserDetailsResponse user,
        String department,
        SubjectSummary subject
) {
    public static TeacherResponse from(Teacher teacher){
        Subject s = teacher.getSubject();
        return new TeacherResponse(
                teacher.getId(),
                UserDetailsResponse.from(teacher.getUser()),
                teacher.getDepartment(),
                s == null ? null : new SubjectSummary(s.getId(), s.getSubjectCode(), s.getSubjectName())

        );
    }
}
