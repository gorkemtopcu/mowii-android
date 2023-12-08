package com.mowii.mowii.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Movie {
    @Id
    private String id;

    private String title;
    private List<String> genres;
    private String director;
    private List<String> actors;
    private String plot;
    private double imdbScore;
    private int releaseYear;

    public Movie() {

    }

    public Movie(String title, List<String> genres, String director, List<String> actors, String plot, double imdbScore, int releaseYear) {
        this.title = title;
        this.genres = genres;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.imdbScore = imdbScore;
        this.releaseYear = releaseYear;
    }
}
