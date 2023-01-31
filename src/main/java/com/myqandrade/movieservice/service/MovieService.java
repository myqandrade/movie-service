package com.myqandrade.movieservice.service;

import com.myqandrade.movieservice.models.dto.MovieDTO;

import java.util.List;
import java.util.UUID;

public interface MovieService {

    public List<MovieDTO> findAll();

    public List<MovieDTO> find(MovieDTO movieDTO);

    public MovieDTO findById(UUID id);
    public MovieDTO save(MovieDTO movieDTO);

    public void delete(UUID id);
    public MovieDTO update(UUID id, MovieDTO movieDTO);

}
