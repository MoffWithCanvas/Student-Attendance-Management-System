package com.codecanvas.student_attendance_management_system.repository;

import com.codecanvas.student_attendance_management_system.model.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    boolean existsByFirstAndLastName(String firstName, String lastName);
}
