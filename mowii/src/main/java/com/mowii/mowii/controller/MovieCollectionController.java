package com.mowii.mowii.controller;

import com.mowii.mowii.model.MovieCollection;
import com.mowii.mowii.repository.MovieCollectionRepository;
import com.mowii.mowii.service.MovieCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie-collection")
public class MovieCollectionController {

    private final MovieCollectionService movieCollectionService;

    @Autowired
    public MovieCollectionController(MovieCollectionService movieCollectionService) {
        this.movieCollectionService = movieCollectionService;
    }

    @GetMapping("/all")
    public List<MovieCollectionRepository> getAllMovieCollections() {
        return movieCollectionService.getAllMovieCollections();
    }

    @GetMapping("/{id}")
    public MovieCollection getMovieCollectionById(@PathVariable String id) {
        return movieCollectionService.getMovieCollectionById(id);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteMovieCollection(@PathVariable String id) {
        movieCollectionService.deleteMovieCollection(id);
    }
}
