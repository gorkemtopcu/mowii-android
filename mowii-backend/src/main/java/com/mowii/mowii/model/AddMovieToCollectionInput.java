package com.mowii.mowii.model;

import lombok.Data;

@Data
public class AddMovieToCollectionInput {

    private String id;
    private String movieId;

    public AddMovieToCollectionInput() {
    }

    public AddMovieToCollectionInput(String id, String movieId) {
        this.id = id;
        this.movieId = movieId;
    }
}
