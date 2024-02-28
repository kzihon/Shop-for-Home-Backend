package com.cogent.ShopforHome.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "_user")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    @ManyToOne
    private Role role;

    private LocalDateTime dateOfRegistration;
    public User() {

    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
//        Calendar calendar = Calendar.getInstance();
//        java.util.Date currentTime = calendar.getTime();
//        this.dateOfRegistration = LocalDateTime.now();
    }
    @PrePersist
    protected void onCreate() {
        dateOfRegistration = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", dateOfRegistration=" + dateOfRegistration +
                '}';
    }
}
