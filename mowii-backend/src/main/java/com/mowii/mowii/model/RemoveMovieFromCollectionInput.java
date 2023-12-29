package com.mowii.mowii.model;

import lombok.Data;

@Data
public class RemoveMovieFromCollectionInput {
    private String id;
    private String movieId;

    public RemoveMovieFromCollectionInput() {
    }

    public RemoveMovieFromCollectionInput(String id, String movieId) {
        this.id = id;
        this.movieId = movieId;
    }
}
