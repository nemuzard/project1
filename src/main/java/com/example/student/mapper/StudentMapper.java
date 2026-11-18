package com.example.student.mapper;

import com.example.student.dto.StudentDto;
import com.example.student.entity.Student;

public class StudentMapper {

    // student entity -> student dto
    public static StudentDto mapToStudentDto(Student student){

        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }
    // student dto -> student entity
    public static Student mapToStudent(StudentDto studentDto){
        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail()
        );
    }
}
