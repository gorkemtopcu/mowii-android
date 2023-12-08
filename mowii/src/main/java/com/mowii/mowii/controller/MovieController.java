package com.mowii.mowii.controller;

import com.mowii.mowii.exception.MovieNotFoundException;
import com.mowii.mowii.model.Movie;
import com.mowii.mowii.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/getAll")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("director/{name}")
    public List<Movie> getMovieByDirector(@PathVariable String name) {
        return movieService.getMovieByDirector(name);
    }

    @GetMapping("genre/{genre}")
    public List<Movie> getMovieByGenre(@PathVariable String genre) {
        return movieService.getMovieByGenre(genre);
    }
    @GetMapping("IMDB/{rating}")
    public List<Movie> getMovieIMDBRating(@PathVariable double rating) {
        return movieService.getMovieByRating(rating);
    }

    @GetMapping("releaseYear/{year}")
    public List<Movie> getMovieIMDBRating(@PathVariable int year) {
        return movieService.getMovieByYear(year);
    }

    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PutMapping("/update/{id}")
    public Movie updateMovie(@PathVariable String id, @RequestBody Movie updatedMovie) {
        return movieService.updateMovie(id, updatedMovie);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable String id) {
        try {
            Movie deletedMovie = movieService.deleteMovie(id);
            return new ResponseEntity<>(deletedMovie, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }
}
