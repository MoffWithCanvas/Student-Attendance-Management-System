package com.codecanvas.student_attendance_management_system.model.entity;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "student_subjects",
    uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "class_subject_id"}))
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_subject_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_subject_id", nullable = false)
    private ClassSubject classSubject;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    protected StudentSubject() {
    }

    public StudentSubject(Student student, ClassSubject classSubject, LocalDate enrollmentDate) {
        this.student = student;
        this.classSubject = classSubject;
        this.enrollmentDate = enrollmentDate;
    }

    public Long getId() {
        return id;
    }
    public Student getStudent() {
        return student;
    }
    public ClassSubject getClassSubject() {
        return classSubject;
    }
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        StudentSubject that = (StudentSubject) o;
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
                "enrollmentDate = " + enrollmentDate + ")";
    }
}
