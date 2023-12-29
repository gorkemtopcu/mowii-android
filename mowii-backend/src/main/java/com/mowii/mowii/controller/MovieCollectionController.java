package com.mowii.mowii.controller;

import com.mowii.mowii.exception.MovieCollectionNotFoundException;
import com.mowii.mowii.exception.MovieNotFoundException;
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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

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

    @GetMapping("/movies/{id}")
    public ResponseEntity<?> getMoviesByCollectionById(@PathVariable String id) {
        try {

            MovieCollection collection = movieCollectionService.getById(id);
            List<Movie> movieList = collection.getMovies();
            return new ResponseEntity<>(movieList, HttpStatus.OK);
        } catch (MovieCollectionNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getMovieCollectionByUserId(@PathVariable String userId) {
        try {
            List<MovieCollection> movieCollections = movieCollectionService.getByUserId(userId);
            return new ResponseEntity<>(movieCollections, HttpStatus.OK);
        } catch (MovieCollectionNotFoundException | UserNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMovieCollection(@RequestBody MovieCollectionCreationInput movieCollectionCreationInput) {
        try {
            User owner = userService.getById(movieCollectionCreationInput.getUserId());
            MovieCollection movieCollection = new MovieCollection(owner.getId(), owner.getName(), movieCollectionCreationInput.getName(),0);
            movieCollectionService.save(movieCollection);
            owner.appendMyCollection(movieCollection.getId());
            userService.save(owner);

            return new ResponseEntity<>(movieCollection, HttpStatus.CREATED);
        } catch (UserNotFoundException | MovieCollectionNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/unlike")
    public ResponseEntity<?> unlikeMovieCollection(@RequestBody MovieCollectionLikeInput movieCollectionLikeInput) {
        try {
            MovieCollection movieCollection = movieCollectionService.getById(movieCollectionLikeInput.getCollectionId());

            // Check if the collection is liked by the user
            if (!movieCollectionService.isCollectionAlreadyLiked(movieCollectionLikeInput.getLikerId(), movieCollection.getId())) {
                return new ResponseEntity<>("Collection is not liked by the user", HttpStatus.BAD_REQUEST);
            }

            movieCollection.setLike(movieCollection.getLike() - 1);
            movieCollectionService.updateMovieCollection(movieCollection);

            User liker = userService.getById(movieCollectionLikeInput.getLikerId());
            liker.removeLikeCollection(movieCollection.getId());
            userService.save(liker);
            return new ResponseEntity<>(movieCollection, HttpStatus.OK);
        } catch (UserNotFoundException | MovieCollectionNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/like")
    public ResponseEntity<?> likeMovieCollection(@RequestBody MovieCollectionLikeInput movieCollectionLikeInput) {
        try {
            MovieCollection movieCollection = movieCollectionService.getById(movieCollectionLikeInput.getCollectionId());

            // Check if the collection is already liked by the user
            if (movieCollectionService.isCollectionAlreadyLiked(movieCollectionLikeInput.getLikerId(), movieCollection.getId())) {
                return new ResponseEntity<>("Collection is already liked by the user", HttpStatus.BAD_REQUEST);
            }

            movieCollection.setLike(movieCollection.getLike() + 1);
            movieCollectionService.updateMovieCollection(movieCollection);

            User liker = userService.getById(movieCollectionLikeInput.getLikerId());
            liker.appendLikeCollection(movieCollection.getId());
            userService.save(liker);
            return new ResponseEntity<>(movieCollection, HttpStatus.CREATED);
        } catch (UserNotFoundException | MovieCollectionNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping("/add-movie")
    public ResponseEntity<?> addMovieToCollections(@RequestBody AddMovieToCollectionsInput input) {
        try {
            Movie movie = movieService.getById(input.getMovieId());

            outerLoop:
            for (String id: input.getIdList()){
                MovieCollection movieCollection = movieCollectionService.getById(id);
                if (movieCollection.getMovies() == null) {
                    movieCollection.setMovies(new ArrayList<>()); // Initialize the list if it's null
                }

                // If movie does not exist in collection: add movie to collection
                for (Movie m: movieCollection.getMovies()) {
                    if (Objects.equals(m.getId(), movie.getId())) continue outerLoop;
                }

                movieCollection.getMovies().add(movie);

                // Update the movie collection
                movieCollectionService.updateMovieCollection(movieCollection);
            }

            return new ResponseEntity<>("Movie added to collections successfully", HttpStatus.OK);

        } catch (MovieCollectionNotFoundException | MovieNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/remove-movie")
    public ResponseEntity<?> removeMovieFromCollection(@RequestBody RemoveMovieFromCollectionInput input) {
        try {
            MovieCollection movieCollection = movieCollectionService.getById(input.getId());

            if (movieCollection.getMovies() == null) {
                return new ResponseEntity<>("Movie is not found in the collection", HttpStatus.BAD_REQUEST);
            }

            for (Movie movie: movieCollection.getMovies()) {
                if(!Objects.equals(movie.getId(), input.getMovieId())) { continue; }
                movieCollection.getMovies().remove(movie);
                movieCollectionService.updateMovieCollection(movieCollection);
                return new ResponseEntity<>("Movie removed from collection successfully", HttpStatus.OK);
            }

            return new ResponseEntity<>("Movie is not found in the collection", HttpStatus.BAD_REQUEST);

        } catch (MovieCollectionNotFoundException | MovieNotFoundException e) {
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
