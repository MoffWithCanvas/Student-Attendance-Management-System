package com.codecanvas.student_attendance_management_system.service;

import com.codecanvas.student_attendance_management_system.model.dto.ParentDetailsRequest;
import com.codecanvas.student_attendance_management_system.model.dto.ParentDetailsResponse;
import com.codecanvas.student_attendance_management_system.model.entity.Parent;
import com.codecanvas.student_attendance_management_system.repository.ParentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ParentService {
    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Transactional
    public ParentDetailsResponse addParent(ParentDetailsRequest request){
        //check if a parent exists
        String firstName = request.firstName().trim().toLowerCase();
        String lastName = request.lastName().trim().toLowerCase();
        if (parentRepository.existsByFirstAndLastName(firstName,lastName)){
            throw new IllegalStateException("Parent already exists");
        }
        //Add a new parent using the parent constructor
        Parent parent = Parent.createParent(
                firstName,
                lastName,
                request.phoneNumber(),
                request.email(),
                request.physicalAddress());
        return ParentDetailsResponse.from(parentRepository.save(parent));
    }
}
