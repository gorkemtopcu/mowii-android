package com.mowii.mowii.model;

import lombok.Data;

@Data
public class MovieCollectionInput {
    private String userId;
    private String name;

    public MovieCollectionInput() {
    }

    public MovieCollectionInput(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
