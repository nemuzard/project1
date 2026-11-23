package com.example.student.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students") // specify the table name
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// student id

    @Column(name="first_name") // without it, default name is the same as String name
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email_id",unique = true,nullable = false)
    private String email;

}
