package com.aleksei.firstsecurityapp.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 2, max = 100, message = "Username should be in range from 2 to 100 characters")
    @Column(name = "username")
    private String username;
    @Min(value = 1900, message = "Year of Birth should be more than 1900")
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    @Column(name = "password")
    private String password;
    @Column(name = "user_role")
    private String role;

    public Person(String username, int yearOfBirth, String password) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
        this.password = password;
    }

    public Person() {
    }
}
