package com.codecanvas.student_attendance_management_system.model.entity;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_subject_id", nullable = false)
    private ClassSubject classSubject;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(name = "lesson_date", nullable = false)
    private LocalDate lessonDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    // MANDATORY DEFAULT CONSTRUCTOR (JPA requirement)
    protected Lesson() {
    }
    /*
    A lesson always has a teacher and classSubject upon creation
    */
    public Lesson(ClassSubject classSubject, Teacher teacher, LocalDate lessonDate, LocalTime startTime, LocalTime endTime) {
        this.classSubject = classSubject;
        this.teacher = teacher;
        this.lessonDate = lessonDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }
    public ClassSubject getClassSubject() {
        return classSubject;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public LocalDate getLessonDate() {
        return lessonDate;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }

    //EQUALS IMPLEMENTATION
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Lesson lesson = (Lesson) o;
        return getId() != null && Objects.equals(getId(), lesson.getId());
    }
    //HASHCODE IMPLEMENTATION
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
    //ToSTRING IMPLEMENTATION

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "lessonDate = " + lessonDate + ", " +
                "startTime = " + startTime + ", " +
                "endTime = " + endTime + ")";
    }
}
