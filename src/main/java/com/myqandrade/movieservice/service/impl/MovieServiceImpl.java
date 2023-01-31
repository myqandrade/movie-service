package com.myqandrade.movieservice.service.impl;

import com.myqandrade.movieservice.exception.MovieAlreadyExistsException;
import com.myqandrade.movieservice.exception.MovieNotFoundException;
import com.myqandrade.movieservice.models.MovieModel;
import com.myqandrade.movieservice.models.dto.MovieDTO;
import com.myqandrade.movieservice.repositories.MovieRepository;
import com.myqandrade.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public List<MovieDTO> findAll(){
        List<MovieModel> movies = movieRepository.findAll();
        List<MovieDTO> moviesDTO =  movies.stream().map(MovieDTO::convert).collect(Collectors.toList());
        if(movies.isEmpty()){
            return null;
        }
        return moviesDTO;
    }

    public List<MovieDTO> find(MovieDTO movieDTO){

        MovieModel movie = MovieModel.convert(movieDTO);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(movie, matcher);

        List<MovieModel> movies = movieRepository.findAll(example);

        if(movies.isEmpty()){
            throw new MovieNotFoundException();
        }

        List<MovieDTO> moviesDTO = movies
                .stream()
                .map(MovieDTO::convert)
                .collect(Collectors.toList());

        return moviesDTO;
    }

    public MovieDTO findById(UUID id){
        Optional<MovieModel> movie = movieRepository.findById(id);

        if(movie.isEmpty()){
            throw new MovieNotFoundException();
        }

        MovieDTO movieDTO = MovieDTO.convert(movie.get());
        return movieDTO;
    }

    public MovieDTO save(MovieDTO movieDTO){
        List<MovieModel> movies = movieRepository.findAll();
        for(MovieModel x : movies){
            if(movieDTO.getTitle().equals(x.getTitle()) && movieDTO.getDirector().equals(x.getDirector())){
                throw new MovieAlreadyExistsException();
            }
        }
        MovieModel movie = MovieModel.convert(movieDTO);

        return MovieDTO.convert(movieRepository.save(movie));
    }

    public void delete(UUID id){
        Optional<MovieModel> movie = movieRepository.findById(id);
        movieRepository.delete(movie.get());
    }

    public MovieDTO update(UUID id, MovieDTO movieDTO){
        return movieRepository
                .findById(id)
                .map( movie -> {
                    movie.setTitle(movieDTO.getTitle());
                    movie.setDirector(movieDTO.getDirector());
                    movie.setGenre(movieDTO.getGenre());
                    movie.setYear(movieDTO.getYear());
                    movieRepository.save(movie);
                    return MovieDTO.convert(movie);
                }).orElseThrow(()-> new MovieNotFoundException());
    }
}
