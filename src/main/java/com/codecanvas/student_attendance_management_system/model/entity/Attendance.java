package com.codecanvas.student_attendance_management_system.model.entity;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "attendances", uniqueConstraints =
        @UniqueConstraint(columnNames = {"student_id", "lesson_id"}),
    indexes = @Index(name = "idx_attendances_lesson", columnList = "lesson_id"))
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher; // the teacher who recorded the lesson

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    @Column(name = "remarks")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AttendanceStatus attendanceStatus;

    protected Attendance() {
    }

    public Attendance(Student student, Lesson lesson, Teacher teacher, LocalDateTime recordedAt, String remarks,
                      AttendanceStatus attendanceStatus) {
        this.student = student;
        this.lesson = lesson;
        this.teacher = teacher;
        this.recordedAt = LocalDateTime.now();
        this.remarks = remarks;
        this.attendanceStatus = attendanceStatus;
    }

    public Long getId() {
        return id;
    }
    public Student getStudent() {
        return student;
    }
    public Lesson getLesson() {
        return lesson;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }
    public String getRemarks() {
        return remarks;
    }
    public AttendanceStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    public void changeStatus(AttendanceStatus status, String remarks){
        if (status == null){
            throw new IllegalArgumentException("status cannot be empty");
        }
        this.attendanceStatus = attendanceStatus;
        this.remarks = remarks;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer()
                .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Attendance that = (Attendance) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "recordedAt = " + recordedAt + ", " +
                "attendanceStatus = " + attendanceStatus + ")";
    }
}
