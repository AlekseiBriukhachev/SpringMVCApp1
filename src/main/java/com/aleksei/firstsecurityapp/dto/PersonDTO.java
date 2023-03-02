package com.aleksei.firstsecurityapp.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PersonDTO {
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 2, max = 100, message = "Username should be in range from 2 to 100 characters")
    private String username;
    @Min(value = 1900, message = "Year of Birth should be more than 1900")
    private int yearOfBirth;
    private String password;
}
