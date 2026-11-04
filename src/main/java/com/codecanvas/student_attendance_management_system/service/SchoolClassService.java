package com.codecanvas.student_attendance_management_system.service;

import com.codecanvas.student_attendance_management_system.model.dto.SchoolClassDetailsRequest;
import com.codecanvas.student_attendance_management_system.model.dto.SchoolClassDetailsResponse;
import com.codecanvas.student_attendance_management_system.model.entity.SchoolClass;
import com.codecanvas.student_attendance_management_system.repository.SchoolClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;

    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    @Transactional
    public SchoolClassDetailsResponse addSchoolClass(SchoolClassDetailsRequest request){
        //check if school class exists by class name
        String className = request.className().trim();
        String academicYear = request.academicYear().trim();

        if (schoolClassRepository.existsByClassNameAndAcademicYear(className, academicYear)){
            throw new IllegalStateException("Class already exists for this academic year");
        }
        //add a new class using a dedicated static factory method in SchoolClass.java
        SchoolClass schoolClass = SchoolClass.createSchoolClass(
                className,
                request.formLevel(),
                academicYear);
        return SchoolClassDetailsResponse.from(schoolClassRepository.save(schoolClass));
    }
}
