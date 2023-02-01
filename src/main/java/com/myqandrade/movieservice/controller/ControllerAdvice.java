package com.myqandrade.movieservice.controller;

import com.myqandrade.movieservice.exception.MovieAlreadyExistsException;
import com.myqandrade.movieservice.exception.MovieNotFoundException;
import com.myqandrade.movieservice.models.dto.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice(basePackages = "com.myqandrade.movieservice.controller")
public class ControllerAdvice {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(MovieNotFoundException.class)
    public ErrorDTO handleMovieNotFound(MovieNotFoundException ex){
        String errorMessage = ex.getMessage();
        return new ErrorDTO(errorMessage);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ErrorDTO handleMovieAlreadyExists(MovieAlreadyExistsException ex){
        String errorMessage = ex.getMessage();
        return new ErrorDTO(errorMessage);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorDTO handleConstraintViolationException(ConstraintViolationException ex){
        List<String> violations = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());

        return new ErrorDTO(violations);
    }
}
