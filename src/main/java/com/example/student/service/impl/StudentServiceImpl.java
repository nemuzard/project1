package com.example.student.service.impl;

import com.example.student.dto.StudentDto;
import com.example.student.entity.Student;
import com.example.student.exception.DuplicateStudentCreationException;
import com.example.student.exception.ResourceNotFoundException;
import com.example.student.mapper.StudentMapper;
import com.example.student.repository.StudentRepository;
import com.example.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Override
    // we get the student information from client side so dto->entity
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        //check if student exist
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new DuplicateStudentCreationException("Check! Record already exists ");
        }
        // save will assign a student id
        Student savedStudent = studentRepository.save(student);// save to database
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Student is not found based on id "+ studentId));

        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();

        return studentList.stream().map(StudentMapper::mapToStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED
    )
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {
        // check if student exist
        Student student=studentRepository.findById(studentId).orElseThrow(()
                ->new ResourceNotFoundException("Student not exist or wrong id -- "+studentId));

        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());

        Student updatedStudentObj = studentRepository.save(student);
        throw new IllegalStateException("Test rollback");
        //return StudentMapper.mapToStudentDto(updatedStudentObj);

    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()
                ->new ResourceNotFoundException("Cannot delete student with id:  "+studentId));

        studentRepository.delete(student);
    }
}
