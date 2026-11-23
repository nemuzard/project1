package com.example.student.repository;

import com.example.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

                                            // 2 params - Entity and id
public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByEmail(String email);
}
