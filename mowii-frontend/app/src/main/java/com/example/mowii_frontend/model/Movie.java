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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(double imdbScore) {
        this.imdbScore = imdbScore;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
