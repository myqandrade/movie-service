package com.myqandrade.movieservice.models.dto;

import com.myqandrade.movieservice.models.MovieModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private String title;
    private DirectorDTO director;
    private String genre;
    private Integer year;

    public static MovieDTO convert(MovieModel movieModel){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(movieModel.getTitle());
        movieDTO.setDirector(DirectorDTO.convert(movieModel.getDirector()));
        movieDTO.setGenre(movieModel.getGenre());
        movieDTO.setYear(movieModel.getYear());

        return movieDTO;
    }
}
