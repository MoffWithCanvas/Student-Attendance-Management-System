package com.codecanvas.student_attendance_management_system.model.entity;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "school_classes")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_class_id")
    private Long id;

    @Column(name = "class_name", nullable = false, unique = true)
    private String className;

    @Column(name = "form_level", nullable = false)
    private String formLevel;

    @Column(name = "academic_year", nullable = false, unique = true)
    private LocalDate academicYear;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_teacher_id", unique = true)
    private Teacher classTeacher;

    //MANDATORY DEFAULT CONSTRUCTOR
    protected SchoolClass() {
    }
    //MANDATORY BUSINESS CONSTRUCTOR
    public SchoolClass(String className, String formLevel, LocalDate academicYear) {
        this.className = className;
        this.formLevel = formLevel;
        this.academicYear = academicYear;
    }

    // OVERLOADED BUSINESS CONSTRUCTOR (Includes optional teacher)
    public SchoolClass(String className, String formlevel, LocalDate academicYear, Teacher classTeacher) {
        this(className, formlevel, academicYear);
        this.classTeacher = classTeacher;
    }

    public Long getId() {
        return id;
    }
    public String getClassName() {
        return className;
    }
    public String getFormLevel() {
        return formLevel;
    }
    public LocalDate getAcademicYear() {
        return academicYear;
    }
    public Teacher getClassTeacher() {
        return classTeacher;
    }

    //THE SERVICE CALLS ON THESE TO SAVE A TEACHER
    public void assignClassTeacher(Teacher teacher){
        if (teacher == null){
            throw new IllegalArgumentException("Teacher cannot be null");
        }
        this.classTeacher = teacher;
    }
    public void removeClassTeacher(){
        this.classTeacher = null;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SchoolClass that = (SchoolClass) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "className = " + className + ", " +
                "formLevel = " + formLevel + ", " +
                "academicYear = " + academicYear + ")";
    }
}
