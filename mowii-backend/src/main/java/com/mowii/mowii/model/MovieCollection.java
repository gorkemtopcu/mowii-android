package com.mowii.mowii.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class MovieCollection {
    @Id
    private String id;

    private String name;
    private int like;

    @DBRef
    private List<Movie> movies = new ArrayList<>();

    private String userId;

    private String userName;

    public MovieCollection() {
    }

    public MovieCollection(String userId, String userName, String name, int like) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.like = like;
    }

    public MovieCollection(String user, String name, List<Movie> movies, int like) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.movies = movies;
        this.like = like;
    }
}
