package com.myqandrade.movieservice.models;

import com.myqandrade.movieservice.models.dto.MovieDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Enter a valid title.")
    private String title;
    @Column(nullable = false)
    @NotEmpty(message = "Enter a valid director.")
    private String director;
    @Column(nullable = false)
    @NotEmpty(message = "Enter a valid genre.")
    private String genre;
    @Column(nullable = false, length = 4)
    @DecimalMin(value = "1900", message = "Enter a valid year")
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
