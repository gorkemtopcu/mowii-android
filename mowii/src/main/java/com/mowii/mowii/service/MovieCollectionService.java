package com.mowii.mowii.service;

import com.mowii.mowii.model.Movie;
import com.mowii.mowii.model.MovieCollection;
import com.mowii.mowii.model.User;
import com.mowii.mowii.repository.MovieCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieCollectionService {
    private final MovieService movieService;
    private final UserService userService;
    private final MovieCollectionRepository movieCollectionRepository;

    @Autowired
    public MovieCollectionService(MovieService movieService, UserService userService,
                                  MovieCollectionRepository movieCollectionRepository) {
        this.movieService = movieService;
        this.userService = userService;
        this.movieCollectionRepository = movieCollectionRepository;
    }

    // MovieCollection-related methods

    public List<MovieCollectionRepository> getAllMovieCollections() {
        return movieCollectionRepository.findAll();
    }

    public MovieCollection getMovieCollectionById(String id) {
        return (MovieCollection) movieCollectionRepository.findById(id).orElse(null);
    }

    public void deleteMovieCollection(String id) {
        movieCollectionRepository.deleteById(id);
    }
}
