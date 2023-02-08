package com.myqandrade.movieservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myqandrade.movieservice.models.dto.DirectorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_director")
public class DirectorModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "director")
    private Set<MovieModel> movies = new HashSet<>();

    public static DirectorModel convert(DirectorDTO directorDTO){
        DirectorModel director = new DirectorModel();
        director.setName(directorDTO.getName());

        directorDTO.getMovies().forEach(movie -> {
            MovieModel convertedMovie = MovieModel.convert(movie);
            director.setMovies(Set.of(convertedMovie));
        });
        return director;
    }
}
