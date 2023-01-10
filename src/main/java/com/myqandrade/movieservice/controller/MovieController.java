package com.myqandrade.movieservice.controller;

import com.myqandrade.movieservice.models.MovieModel;
import com.myqandrade.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movie-service")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<MovieModel>> findALl(){
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieModel> findById(@PathVariable(name = "id") UUID id){
        return movieService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody MovieModel movie){
        movieService.save(movie);
    }
}
