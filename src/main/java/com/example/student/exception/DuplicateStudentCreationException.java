package com.example.student.exception;


public class DuplicateStudentCreationException extends RuntimeException {
    public DuplicateStudentCreationException(String message) {
        super(message);
    }
}
