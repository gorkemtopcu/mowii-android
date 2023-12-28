package com.mowii.mowii.model;

import lombok.Data;

import java.util.List;

@Data
public class AddMovieToCollectionsInput {

    private List<String> idList;
    private String movieId;

    public AddMovieToCollectionsInput() {
    }

    public AddMovieToCollectionsInput(List<String> idList, String movieId) {
        this.idList = idList;
        this.movieId = movieId;
    }
}
