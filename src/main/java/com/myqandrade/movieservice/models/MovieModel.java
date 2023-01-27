package com.myqandrade.movieservice.models;

import com.myqandrade.movieservice.models.dto.MovieDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_MOVIE")
@AllArgsConstructor
@NoArgsConstructor
public class MovieModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String director;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false, length = 4)
    private Integer year;

    public static MovieModel convert(MovieDTO movieDTO){
        MovieModel movieModel = new MovieModel();
        movieModel.setTitle(movieDTO.getTitle());
        movieModel.setDirector(movieDTO.getDirector());
        movieModel.setGenre(movieDTO.getGenre());
        movieModel.setYear(movieDTO.getYear());

        return movieModel;
    }

}
