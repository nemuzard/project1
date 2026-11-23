package com.example.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    // accepts this kind of exception.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFound(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateStudentCreationException.class)
    public ResponseEntity<String> handleDuplicates(DuplicateStudentCreationException ex){
        return new ResponseEntity<>("Student already exist: "+ex.getMessage(),HttpStatus.CONFLICT);
    }
}
