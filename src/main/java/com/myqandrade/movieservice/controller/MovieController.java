package com.myqandrade.movieservice.controller;

import com.myqandrade.movieservice.models.MovieModel;
import com.myqandrade.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/findAll")
    public ResponseEntity<List<MovieModel>> findALl(){
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/find")
    public ResponseEntity find(MovieModel movieModel){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(movieModel, matcher);
        List<MovieModel> movies = movieService.find(example);

        return ResponseEntity.ok(movies);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<MovieModel> findById(@PathVariable(value = "id") UUID id){
        if(Objects.isNull(movieService.findById(id))){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Validated MovieModel movie){
       if(Objects.isNull(movieService.save(movie))){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie already exists");
       }
       return ResponseEntity.ok(movieService.save(movie));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id){
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity update(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Validated MovieModel movieModel){
        if(Objects.isNull(movieModel)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(movieService.update(id, movieModel));
    }
}
