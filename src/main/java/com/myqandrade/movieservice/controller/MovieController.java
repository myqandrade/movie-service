package com.myqandrade.movieservice.controller;

import com.myqandrade.movieservice.models.MovieModel;
import com.myqandrade.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieModel>> findALl(){
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieModel> findById(@PathVariable(value = "id") UUID id){
        return movieService.findById(id);
    }

    @PostMapping
    public ResponseEntity<MovieModel> save(@RequestBody @Validated MovieModel movie){
       return movieService.save(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieModel> delete(@PathVariable(value = "id") UUID id){
        return movieService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Validated MovieModel movieModel){
        if(Objects.isNull(movieModel)){
            return ResponseEntity.badRequest().build();
        }
        movieService.update(id, movieModel);
        return ResponseEntity.noContent().build();
    }
}
