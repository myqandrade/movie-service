package com.myqandrade.movieservice.exception;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException() {
        super("Movie already Exists.");
    }
}
