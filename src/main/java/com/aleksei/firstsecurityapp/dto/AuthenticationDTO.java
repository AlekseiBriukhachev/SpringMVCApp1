package com.aleksei.firstsecurityapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AuthenticationDTO {
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 2, max = 100, message = "Username should be in range from 2 to 100 characters")
    private String username;
    private String password;
}
