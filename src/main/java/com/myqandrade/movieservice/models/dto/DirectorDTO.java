package com.myqandrade.movieservice.models.dto;

import com.myqandrade.movieservice.models.DirectorModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectorDTO {

    private String name;
    private Set<MovieDTO> movies = new HashSet<>();

    public static DirectorDTO convert(DirectorModel directorModel){
        DirectorDTO director = new DirectorDTO();
        director.setName(directorModel.getName());
        directorModel.getMovies().forEach(movieModel -> {
            MovieDTO movieDTO = MovieDTO.convert(movieModel);
            director.setMovies(Set.of(movieDTO));
        });
        return director;
    }

    public Set<MovieDTO> setMovies(Set<MovieDTO> movies){
        this.movies = movies;
        return movies;
    }
}
