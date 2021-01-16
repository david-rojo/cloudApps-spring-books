package com.cloudapps.springbooks.dtos.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "Username is mandatory")
    private String username;
    
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;

}
