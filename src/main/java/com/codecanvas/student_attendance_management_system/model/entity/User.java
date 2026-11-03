package com.codecanvas.student_attendance_management_system.model.entity;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

import static com.codecanvas.student_attendance_management_system.model.entity.Validation.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    protected User() {
    }

    private User(String firstName, String lastName, String username, String address, LocalDate dateOfBirth, String email, String passwordHash, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUsername() {
        return username;
    }
    public String getAddress() {
        return address;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getEmail() {
        return email;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public Role getRole() {
        return role;
    }

    //Static factory method to create a new user since I did not include setters
    public static User create(String firstName, String lastName, String username, String address,
                                     LocalDate dateOfBirth, String email, String passwordHash, Role role){

        /*
        requireNonBlank validation method to check if values are blank
        and return a trimmed value
         */
        String cleanFirstName = requireNonBlank(firstName, "First Name");
        String cleanLastName = requireNonBlank(lastName, "Last name");
        String cleanUsername = requireNonBlank(username, "Username").toLowerCase();
        String cleanAddress = requireNonBlank(address, "Address");
        String cleanEmail = requireNonBlank(email, "Email").toLowerCase();
        String cleanHash = requireNonBlank(passwordHash, "Password hash");

        if (dateOfBirth == null){
            throw new IllegalArgumentException("Date cannot be empty");
        }
        if (dateOfBirth.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
        return new User(cleanFirstName, cleanLastName, cleanUsername, cleanAddress, dateOfBirth,
                cleanEmail, cleanHash, role);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "username = " + username + ", " +
                "address = " + address + ", " +
                "dateOfBirth = " + dateOfBirth + ", " +
                "email = " + email + ", " +
                "role = " + role + ")";
    }
}
