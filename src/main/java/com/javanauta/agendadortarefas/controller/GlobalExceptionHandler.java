package com.javanauta.agendadortarefas.controller;


import com.javanauta.agendadortarefas.infraestructure.exceptions.ResourceNotFoundException;
import com.javanauta.agendadortarefas.infraestructure.exceptions.UnauthorazedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorazedException.class)
    public ResponseEntity<String> handleUnauthorazedException(UnauthorazedException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
