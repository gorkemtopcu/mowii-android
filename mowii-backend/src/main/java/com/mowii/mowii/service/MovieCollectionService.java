package com.mowii.mowii.service;

import com.mowii.mowii.exception.MovieCollectionNotFoundException;
import com.mowii.mowii.exception.UserNotFoundException;
import com.mowii.mowii.model.MovieCollection;
import com.mowii.mowii.model.User;
import com.mowii.mowii.repository.MovieCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieCollectionService implements MowiiService<MovieCollection> {
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

    public boolean isCollectionAlreadyLiked(String likerId, MovieCollection movieCollection) {
        User liker = userService.getById(likerId);
        List<MovieCollection> likedCollections = liker.getLikedCollections();
        // Check if the collection is already in the liked collections
        return likedCollections.contains(movieCollection);
    }

    @Override
    public MovieCollection save(MovieCollection movieCollection) {
        return movieCollectionRepository.save(movieCollection);

    }

    @Override
    public MovieCollection getById(String id) {
        Optional<MovieCollection> movieCollectionOptional = movieCollectionRepository.findById(id);

        if (movieCollectionOptional.isPresent()) {
            return movieCollectionOptional.get();
        } else {
            // User not found, throw an exception or return a special value
            throw new MovieCollectionNotFoundException("Movie collection not found with ID: " + id);
        }
    }

    @Override
    public List<MovieCollection> getAll() {
        return movieCollectionRepository.findAll();
    }

    @Override
    public MovieCollection update(String id, MovieCollection updatedItem) {
        return null;
    }

    @Override
    public MovieCollection delete(String id) {
        MovieCollection movieCollection = getById(id);
        movieCollectionRepository.deleteById(id);
        return movieCollection;
    }



    public void updateMovieCollection(MovieCollection movieCollection) {
        movieCollectionRepository.save(movieCollection);
    }


    public void deleteCollectionsOfUser(String userId) {
        List<MovieCollection> userCollections = movieCollectionRepository.findAllByUserId(userId);
        for (MovieCollection collection : userCollections) {
            movieCollectionRepository.deleteById(collection.getId());
        }


    }
}
