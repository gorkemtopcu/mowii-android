package com.example.mowii_frontend.model;

import java.util.List;

public class Movie {
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

    public Movie(String id, String title, List<String> genres, String director, List<String> actors, String plot, double imdbScore, int releaseYear) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.imdbScore = imdbScore;
        this.releaseYear = releaseYear;
    }
}
