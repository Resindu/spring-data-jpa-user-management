package com.example.springdatajpausermanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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




}
