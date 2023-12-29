package com.example.mowii_frontend.model;

import com.google.gson.annotations.SerializedName;

public class MovieCollectionLikeInput {
    private String collectionId;

    private String likerId;

    public MovieCollectionLikeInput(String collectionId, String likerId) {
        this.collectionId = collectionId;
        this.likerId = likerId;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getLikerId() {
        return likerId;
    }

    public void setLikerId(String likerId) {
        this.likerId = likerId;
    }
}
