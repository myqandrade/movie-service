package com.myqandrade.movieservice.controller;

import com.myqandrade.movieservice.models.dto.MovieDTO;
import com.myqandrade.movieservice.models.dto.NewTitleDTO;
import com.myqandrade.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/findAll")
    @ResponseStatus(OK)
    public ResponseEntity findALl(){
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/find")
    @ResponseStatus(OK)
    public ResponseEntity find(MovieDTO movieDTO){
        List<MovieDTO> movies = movieService.find(movieDTO);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("findById/{id}")
    @ResponseStatus(OK)
    public ResponseEntity findById(@PathVariable(value = "id") UUID id){
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping("/save")
    @ResponseStatus(CREATED)
    public ResponseEntity<?> save(@RequestBody @Validated MovieDTO movieDTO){
       return ResponseEntity.status(HttpStatus.CREATED).body(movieService.save(movieDTO));
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity delete(@PathVariable(value = "id") UUID id){
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity update(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Validated MovieDTO movieDTO){
        return ResponseEntity.ok(movieService.update(id, movieDTO));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateTitle(@PathVariable UUID id, @RequestBody NewTitleDTO newTitleDTO){
        movieService.newTitle(id, newTitleDTO);
    }
}
