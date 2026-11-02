package com.codecanvas.student_attendance_management_system.model.entity;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;
import java.util.Objects;

@Entity
@Table(name = "class_subjects",
    uniqueConstraints = @UniqueConstraint(columnNames = {"class_id", "subject_id"}))
public class ClassSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_subject_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "is_whole_class_required", nullable = false)
    private Boolean wholeClassRequired;

    //MANDATORY DEFAULT CONSTRUCTOR
    public ClassSubject() {
    }

    //THE SOLE BUSINESS CONSTRUCTOR
    public ClassSubject(SchoolClass schoolClass, Subject subject, Boolean wholeClassRequired) {
        this.schoolClass = schoolClass;
        this.subject = subject;
        this.wholeClassRequired = wholeClassRequired;
    }

    //SAFE PUBLIC GETTERS
    public Long getId() {
        return id;
    }
    public SchoolClass getSchoolClass() {
        return schoolClass;
    }
    public Subject getSubject() {
        return subject;
    }
    public Boolean getWholeClassRequired() {
        return wholeClassRequired;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ClassSubject that = (ClassSubject) o;
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
                "wholeClassRequired = " + wholeClassRequired + ")";
    }
}
