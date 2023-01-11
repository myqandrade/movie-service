package com.myqandrade.movieservice.service;

import com.myqandrade.movieservice.models.MovieModel;
import com.myqandrade.movieservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<MovieModel> movie = movieRepository.findById(id);
        if(movie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie.get(), HttpStatus.OK);
    }

    public ResponseEntity<MovieModel> save(MovieModel movie){
        List<MovieModel> movies = movieRepository.findAll();
        for(MovieModel x : movies){
            if(movie.getTitle().equals(x.getTitle())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(movieRepository.save(movie), HttpStatus.CREATED);
    }

    public ResponseEntity<MovieModel> delete(UUID id){
        Optional<MovieModel> movie = movieRepository.findById(id);
        if(movie.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        movieRepository.delete(movie.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<MovieModel> update(UUID id, MovieModel movieModel){
        Optional<MovieModel> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        movieModel.setId(movie.get().getId());
        return new ResponseEntity<>(movieRepository.save(movieModel), HttpStatus.OK);
    }
}
