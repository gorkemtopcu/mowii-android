package com.example.mowii_frontend.model;

import java.util.List;

public class AddMovieToCollectionInput {
    private List<String> idList;
    private String movieId;

    public AddMovieToCollectionInput() {
    }

    public AddMovieToCollectionInput(List<String> idList, String movieId) {
        this.idList = idList;
        this.movieId = movieId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public List<String> getId() {
        return idList;
    }

    public void setId(List<String> idList) {
        this.idList = idList;
    }
}
