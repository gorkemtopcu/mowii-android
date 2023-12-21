package com.mowii.mowii.model;

import lombok.Data;

@Data
public class MovieCollectionLikeInput {
    private String collectionId;
    private String likerId;

    public MovieCollectionLikeInput(){ }

    public MovieCollectionLikeInput(String collectionId, String likerId) {
        this.collectionId = collectionId;
        this.likerId = likerId;
    }
}
