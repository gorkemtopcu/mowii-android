package com.example.mowii_frontend;

public class Movie {

    String movieName;
    String director;
    int releaseYear;

    public Movie(String movieName, String director, int releaseYear) {
        this.movieName = movieName;
        this.director = director;
        this.releaseYear = releaseYear;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
