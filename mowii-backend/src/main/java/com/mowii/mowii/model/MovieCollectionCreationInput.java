package com.mowii.mowii.model;

import lombok.Data;

@Data
public class MovieCollectionCreationInput {
    private String userId;
    private String name;

    public MovieCollectionCreationInput() {
    }

    public MovieCollectionCreationInput(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}

