package com.codecanvas.student_attendance_management_system.model.entity;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student{
    @Id
    @Column(name = "student_id")
    private Long id;  //same value as users.id

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "student_id")
    private User user;

    @Column(nullable = false)
    private LocalDate enrollmentDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "school_class_id", nullable = false)
    private SchoolClass schoolClass;

    protected Student() {
    }
    public Student(User user, LocalDate enrollmentDate, SchoolClass schoolClass) {
        this.user = user;
        this.enrollmentDate = enrollmentDate;
        this.schoolClass = schoolClass;
    }

    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Student student = (Student) o;
        return getId() != null && Objects.equals(getId(), student.getId());
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
