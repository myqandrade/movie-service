package com.myqandrade.movieservice.controller;

import com.myqandrade.movieservice.exception.MovieAlreadyExistsException;
import com.myqandrade.movieservice.exception.MovieNotFoundException;
import com.myqandrade.movieservice.models.dto.ErrorDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice(basePackages = "com.myqandrade.movieservice.controller")
public class ControllerAdvice {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(MovieNotFoundException.class)
    public ErrorDTO handleMovieNotFound(MovieNotFoundException movieNotFoundException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(NOT_FOUND.value());
        errorDTO.setMessage("Movie not found");
        errorDTO.setTimestamp(new Date());

        return errorDTO;
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ErrorDTO handleMovieAlreadyExists(MovieAlreadyExistsException movieAlreadyExistsException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(BAD_REQUEST.value());
        errorDTO.setMessage("Movie already registered");
        errorDTO.setTimestamp(new Date());

        return errorDTO;
    }
}
