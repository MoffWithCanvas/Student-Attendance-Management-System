package com.codecanvas.student_attendance_management_system.service;

import com.codecanvas.student_attendance_management_system.model.dto.SubjectDetailsRequest;
import com.codecanvas.student_attendance_management_system.model.dto.SubjectDetailsResponse;
import com.codecanvas.student_attendance_management_system.model.entity.Subject;
import com.codecanvas.student_attendance_management_system.model.entity.SubjectType;
import com.codecanvas.student_attendance_management_system.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public SubjectDetailsResponse addSubject(SubjectDetailsRequest request){
        //Check if subject exist by subject code
        String subjectCode = request.subjectCode().trim().toUpperCase();
        if (subjectRepository.existsBySubjectCode(subjectCode)){
            throw new IllegalStateException("Subject already exists");
        }
        //add a new subject using a dedicated static factory method in Subject.java
        Subject subject = Subject.createSubject(
                subjectCode,
                request.subjectName(),
                request.credits(),
                request.subjectType());
        return SubjectDetailsResponse.from(subjectRepository.save(subject));
    }
}
