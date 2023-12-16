package com.example.mowii_frontend.model;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    private String id;
    private String name;
    private int like;
    private List<Movie> movies = new ArrayList<>();
    private User user;

    public Collection() {

    }

    public Collection(User user, String name, int like) {
        this.user = user;
        this.name = name;
        this.like = like;
    }

    public Collection(User user, String name, List<Movie> movies, int like) {
        this.user = user;
        this.name = name;
        this.movies = movies;
        this.like = like;
    }
}
