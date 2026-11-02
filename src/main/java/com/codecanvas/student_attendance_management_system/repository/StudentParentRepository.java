package com.codecanvas.student_attendance_management_system.repository;

import com.codecanvas.student_attendance_management_system.model.entity.StudentParent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentParentRepository extends JpaRepository<StudentParent, Long> {
}
