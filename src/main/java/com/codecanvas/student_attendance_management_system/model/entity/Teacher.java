package com.codecanvas.student_attendance_management_system.model.entity;

import com.codecanvas.student_attendance_management_system.model.dto.UserDetailsResponse;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;
import java.util.Objects;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    private Long id; // same values as users.id

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "teacher_id")
    private User user;

    @Column(nullable = false)
    private String department;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // MANDATORY DEFAULT CONSTRUCTOR (JPA requirement)
    protected Teacher() {
    }

    public Teacher(User user, String department) {
        this.user = user;
        this.department = department;
    }
    /*
    OPTIONAL OVERLOADED CONSTRUCTOR
    A teacher always needs a subject upon creation
     */
    private Teacher(User user, String department, Subject subject) {
        this(user, department);
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public String getDepartment() {
        return department;
    }
    public Subject getSubject() {
        return subject;
    }

    //static factory method to create a teacher
    public static Teacher createTeacher(User user, String department, Subject subject){
        String cleanDepartment = Validation.requireNonBlank(department, "Department");
        if (user == null){
            throw new IllegalArgumentException("User details required");
        }
        // this role check guarantees that STUDENT or ADMIN cannot have a TEACHER profile
        if (user.getRole() != Role.TEACHER){
            throw new IllegalArgumentException("User must have TEACHER role");
        }
        if (subject == null){
            throw new IllegalArgumentException("Subject required");
        }

        return new Teacher(user, cleanDepartment, subject);
    }

    //EQUALS AND HASHCODE IMPLEMENTATION
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Teacher teacher = (Teacher) o;
        return getId() != null && Objects.equals(getId(), teacher.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
    /*
    ToSTRING IMPLEMENTATION
    Strictly parent scalar values
    */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "department = " + department + ")";
    }
}
