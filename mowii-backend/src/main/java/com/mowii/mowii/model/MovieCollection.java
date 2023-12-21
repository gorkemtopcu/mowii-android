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

    private String user_id;

    private String user_name;

    public MovieCollection() {
    }

    public MovieCollection(String user_id, String user_name, String name, int like) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.name = name;
        this.like = like;
    }

    public MovieCollection(String user, String name, List<Movie> movies, int like) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.name = name;
        this.movies = movies;
        this.like = like;
    }
}
