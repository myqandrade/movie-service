package com.myqandrade.movieservice.service;

import com.myqandrade.movieservice.models.MovieModel;
import com.myqandrade.movieservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieModel> findAll(){
        List<MovieModel> movies = movieRepository.findAll();
        if(movies.isEmpty()){
            return null;
        }
        return movies;
    }

    public List<MovieModel> find(Example example){
        List<MovieModel> movies = movieRepository.findAll(example);
        if(movies.isEmpty()){
            return null;
        }
        return movies;
    }

    public MovieModel findById(UUID id){
        Optional<MovieModel> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    public MovieModel save(MovieModel movie){
        List<MovieModel> movies = movieRepository.findAll();
        for(MovieModel x : movies){
            if(movie.getTitle().equals(x.getTitle()) && movie.getDirector().equals(x.getDirector())){
                return null;
            }
        }
        return movieRepository.save(movie);
    }

    public void delete(UUID id){
        Optional<MovieModel> movie = movieRepository.findById(id);
        movieRepository.delete(movie.get());
    }

    public MovieModel update(UUID id, MovieModel movieModel){
        return movieRepository
                .findById(id)
                .map( movie -> {
                    movieModel.setId(movie.getId());
                    movieRepository.save(movieModel);
                    return movieModel;
                }).orElseGet( () -> null );
    }
}
