package com.mowii.mowii.controller;

import com.mowii.mowii.exception.MovieCollectionNotFoundException;
import com.mowii.mowii.exception.UserNotFoundException;
import com.mowii.mowii.model.*;
import com.mowii.mowii.service.MovieCollectionService;
import com.mowii.mowii.service.MovieService;
import com.mowii.mowii.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie-collection")
public class MovieCollectionController {

    private final MovieCollectionService movieCollectionService;
    private final UserService userService;
    private final MovieService movieService;

    @Autowired
    public MovieCollectionController(MovieCollectionService movieCollectionService, UserService userService, MovieService movieService) {
        this.movieCollectionService = movieCollectionService;
        this.userService = userService;
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public List<MovieCollection> getAllMovieCollections() {
        return movieCollectionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieCollectionById(@PathVariable String id) {
        try {
            MovieCollection movieCollection = movieCollectionService.getById(id);
            return new ResponseEntity<>(movieCollection, HttpStatus.OK);
        } catch (MovieCollectionNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMovieCollection(@RequestBody MovieCollectionCreationInput movieCollectionCreationInput) {
        try {
            User owner = userService.getById(movieCollectionCreationInput.getUserId());
            MovieCollection movieCollection = new MovieCollection(owner, movieCollectionCreationInput.getName(),0);
            movieCollectionService.create(movieCollection);
            return new ResponseEntity<>(movieCollection, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/like")
    public ResponseEntity<?> likeMovieCollection(@RequestBody MovieCollectionLikeInput movieCollectionLikeInput) {
        try {
            MovieCollection movieCollection = movieCollectionService.getById(movieCollectionLikeInput.getCollectionId());
            movieCollection.setLike(movieCollection.getLike() + 1);
            User liker = userService.getById(movieCollectionLikeInput.getLikerId());
            movieCollectionService.create(movieCollection);
            return new ResponseEntity<>(movieCollection, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/add-movie")
    public ResponseEntity<?> addMovieToCollection(@RequestBody AddMovieToCollectionInput input) {
        try {
            Movie movie = movieService.getById(input.getMovieId());
            MovieCollection movieCollection = movieCollectionService.getById(input.getId());

            if (movieCollection.getMovies() == null) {
                movieCollection.setMovies(new ArrayList<>()); // Initialize the list if it's null
            }

            if (movieCollection.getMovies().contains(movie)) {
                return new ResponseEntity<>("Movie is already in the collection", HttpStatus.BAD_REQUEST);
            }

            // Add the movie to the collection
            movieCollection.getMovies().add(movie);

            // Update the movie collection
            movieCollectionService.updateMovieCollection(movieCollection);

            return new ResponseEntity<>("Movie added to collection successfully", HttpStatus.OK);

        } catch (UserNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovieCollection(@PathVariable String id) {
        try {
            MovieCollection deletedCollection = movieCollectionService.delete(id);
            return new ResponseEntity<>(deletedCollection, HttpStatus.OK);
        } catch (MovieCollectionNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }
}
