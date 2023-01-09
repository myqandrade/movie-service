package com.myqandrade.movieservice.controller;

import com.myqandrade.movieservice.entities.Movie;
import com.myqandrade.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie-service")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> findALl(){
        return movieService.findAll();
    }

    @PostMapping
    public void save(@RequestBody Movie movie){
        movieService.save(movie);
    }
}
