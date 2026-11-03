package com.codecanvas.student_attendance_management_system.model.entity;

import java.time.LocalDate;
/*
    A validation class to check if values for field names are not null and blank
    It will include more validation checks as the project continues
 */
public class Validation {
    private Validation(){}

    static String requireNonBlank(String value, String fieldName){
        if (value == null || value.isBlank()){
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
        return value.trim();
    }
}
