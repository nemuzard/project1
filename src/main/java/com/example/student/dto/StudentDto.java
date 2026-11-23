package com.example.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    @NotBlank(message = "Please enter first name")
    @Size(min=3,message = "must >= 3 characters")
    private String firstName;
    private String lastName;
    @NotBlank(message = "Email is required.")
    @Email(message = "Email format is invalid.")
    private String email;
}
