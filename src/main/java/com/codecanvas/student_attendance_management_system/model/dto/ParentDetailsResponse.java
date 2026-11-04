package com.codecanvas.student_attendance_management_system.model.dto;

import com.codecanvas.student_attendance_management_system.model.entity.Parent;

public record ParentDetailsResponse(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String physicalAddress
) {
    public static ParentDetailsResponse from(Parent parent){
        return new ParentDetailsResponse(
                parent.getId(),
                parent.getFirstName(),
                parent.getLastName(),
                parent.getPhoneNumber(),
                parent.getEmail(),
                parent.getPhysicalAddress());
    }
}
