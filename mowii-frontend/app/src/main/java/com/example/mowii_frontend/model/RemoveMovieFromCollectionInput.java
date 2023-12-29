package com.example.mowii_frontend.model;

public class RemoveMovieFromCollectionInput {

    private String id;
    private String movieId;

    public RemoveMovieFromCollectionInput() {
    }

    public RemoveMovieFromCollectionInput(String id, String movieId) {
        this.id = id;
        this.movieId = movieId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
