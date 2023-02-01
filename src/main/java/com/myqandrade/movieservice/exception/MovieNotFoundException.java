package com.myqandrade.movieservice.exception;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException() {
        super("Movie not found.");
    }
}
