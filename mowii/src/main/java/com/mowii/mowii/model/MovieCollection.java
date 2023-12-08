package com.mowii.mowii.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class MovieCollection {
    @Id
    private String id;
    private String userId;
    private String name;

    @DBRef
    private List<Movie> movies;

    public MovieCollection() {
    }

    public MovieCollection(String userId, String name, List<Movie> movies) {
        this.userId = userId;
        this.name = name;
        this.movies = movies;
    }

}
