package com.example.schemaRegistry.controllers;

import com.example.schemaRegistry.exceptions.SchemaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionController {
    @ExceptionHandler(SchemaNotFoundException.class)
    public ResponseEntity<String> handleNotFound(SchemaNotFoundException e) {
        return new ResponseEntity<>("schema not found", HttpStatus.NOT_FOUND);

    }
}
