package com.mowii.mowii.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Movie {
    @Id
    private String id;
    private String title;
    private String genre;
    private String director;
    private String actor;
    private double imdbScore;
    private int releaseYear;

    public Movie() {
    }

    public Movie(String title, String genre, String director, String actor, double imdbScore, int releaseYear) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.actor = actor;
        this.imdbScore = imdbScore;
        this.releaseYear = releaseYear;
    }

}
