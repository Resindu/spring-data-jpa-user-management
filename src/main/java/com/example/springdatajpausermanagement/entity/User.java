package com.example.springdatajpausermanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
@Table(
        name = "user",
        uniqueConstraints = @UniqueConstraint(
                name = "emailId_unique",
                columnNames = "Email"
        )
)
public class User {

    @Id
    @Column(name="Id", nullable = false)
    private int id;
    @Column(name="FirstName")
    private String firstName;
    @Column(name="LastName")
    private String lastName;
    @Column(
            name="Email",
            nullable = false
    )
    private String email;
    @Column(name="Username")
    private String username;
    @Column(name="Password")
    private String password;

    public User(int id, String firstName, String lastName, String email, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public User() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
