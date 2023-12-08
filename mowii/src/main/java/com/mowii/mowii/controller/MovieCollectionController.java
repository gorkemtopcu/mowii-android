package com.mowii.mowii.controller;

import com.mowii.mowii.exception.UserNotFoundException;
import com.mowii.mowii.model.MovieCollection;
import com.mowii.mowii.model.MovieCollectionInput;
import com.mowii.mowii.model.User;
import com.mowii.mowii.repository.MovieCollectionRepository;
import com.mowii.mowii.service.MovieCollectionService;
import com.mowii.mowii.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie-collection")
public class MovieCollectionController {

    private final MovieCollectionService movieCollectionService;
    private final UserService userService;

    @Autowired
    public MovieCollectionController(MovieCollectionService movieCollectionService) {
        this.movieCollectionService = movieCollectionService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<MovieCollectionRepository> getAllMovieCollections() {
        return movieCollectionService.getAllMovieCollections();
    }

    @GetMapping("/{id}")
    public MovieCollection getMovieCollectionById(@PathVariable String id) {
        return movieCollectionService.getMovieCollectionById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMovieCollection(@RequestBody MovieCollectionInput movieCollectionInput) {
        try {
            User owner = userService.getUserById(movieCollectionInput.getUserId());
            MovieCollection movieCollection = new MovieCollection(owner,movieCollectionInput.getName(),0);
            return new ResponseEntity<>(movieCollection, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{id}")
    public void deleteMovieCollection(@PathVariable String id) {
        movieCollectionService.deleteMovieCollection(id);
    }
}
