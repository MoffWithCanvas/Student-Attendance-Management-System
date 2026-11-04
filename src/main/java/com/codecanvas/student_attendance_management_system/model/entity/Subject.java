package com.codecanvas.student_attendance_management_system.model.entity;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;
import java.util.Objects;

import static com.codecanvas.student_attendance_management_system.model.entity.Validation.*;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_code", nullable = false, unique = true)
    private String subjectCode;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(nullable = false)
    private int credits;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type", nullable = false)
    private SubjectType subjectType;

    public Subject() {
    }

    private Subject(String subjectCode, String subjectName, int credits, SubjectType subjectType) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.subjectType = subjectType;
    }

    public Long getId() {
        return id;
    }
    public String getSubjectCode() {
        return subjectCode;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public int getCredits() {
        return credits;
    }
    public SubjectType getSubjectType() {
        return subjectType;
    }

    //static factory method to handle subject creation
    public static Subject createSubject(String subjectCode, String subjectName, int credits, SubjectType subjectType){
        String cleanSubjectCode = requireNonBlank(subjectCode, "Subject code").toUpperCase();
        String cleanSubjectName = requireNonBlank(subjectName, "Subject name");

        if (subjectType == null){
            throw new IllegalArgumentException("Subject type required");
        }
        if (credits < 1){
            throw new IllegalArgumentException("Credits must be at least 1");
        }
        return new Subject(cleanSubjectCode, cleanSubjectName, credits, subjectType);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Subject subject = (Subject) o;
        return getId() != null && Objects.equals(getId(), subject.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "subjectCode = " + subjectCode + ", " +
                "subjectName = " + subjectName + ", " +
                "credits = " + credits + ", " +
                "subjectType = " + subjectType + ")";
    }
}
