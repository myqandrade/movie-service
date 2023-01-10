package com.myqandrade.movieservice.service;

import com.myqandrade.movieservice.models.MovieModel;
import com.myqandrade.movieservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieModel> findAll(){
        return movieRepository.findAll();
    }

    public ResponseEntity<MovieModel> findById(UUID id){
        var movie = movieRepository.findById(id).get();
        return ResponseEntity.ok().body(movie);
    }

    public void save(MovieModel movie){
        movieRepository.save(movie);
    }
}
