package com.myqandrade.movieservice.service;

import com.myqandrade.movieservice.models.MovieModel;
import com.myqandrade.movieservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public ResponseEntity<List<MovieModel>> findAll(){
        return new ResponseEntity<List<MovieModel>>(
                movieRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<MovieModel> findById(UUID id){
        var movie = movieRepository.findById(id).get();
        return ResponseEntity.ok().body(movie);
    }

    public void save(MovieModel movie){
        movieRepository.save(movie);
    }
}
