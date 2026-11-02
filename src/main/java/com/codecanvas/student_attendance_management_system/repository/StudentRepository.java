package com.codecanvas.student_attendance_management_system.repository;

import com.codecanvas.student_attendance_management_system.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
