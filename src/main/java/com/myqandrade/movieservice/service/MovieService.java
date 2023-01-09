package com.myqandrade.movieservice.service;

import com.myqandrade.movieservice.entities.Movie;
import com.myqandrade.movieservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public void save(Movie movie){
        movieRepository.save(movie);
    }
}
