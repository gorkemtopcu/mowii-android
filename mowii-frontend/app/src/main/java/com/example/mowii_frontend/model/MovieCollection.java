package com.example.mowii_frontend.model;

import java.util.ArrayList;
import java.util.List;

public class MovieCollection {
    private String id;
    private String name;
    private int like;
    private List<Movie> movies = new ArrayList<>();
    private String userName;
    private String userId;

    public MovieCollection() {

    }

    public MovieCollection(String userId, String userName, String name, int like) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.like = like;
    }
    public MovieCollection(String userId, String userName, String name, List<Movie> movies, int like) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.movies = movies;
        this.like = like;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {return userName;}

}
