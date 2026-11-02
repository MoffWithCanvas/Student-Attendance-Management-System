package com.codecanvas.student_attendance_management_system.repository;

import com.codecanvas.student_attendance_management_system.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
