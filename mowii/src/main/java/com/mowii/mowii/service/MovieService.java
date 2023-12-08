package com.mowii.mowii.service;

import com.mowii.mowii.exception.MovieNotFoundException;
import com.mowii.mowii.model.Movie;
import com.mowii.mowii.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(String id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.orElse(null);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(String id, Movie updatedMovie) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (optionalMovie.isPresent()) {
            Movie existingMovie = optionalMovie.get();
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setGenres(updatedMovie.getGenres());
            existingMovie.setDirector(updatedMovie.getDirector());
            existingMovie.setActors(updatedMovie.getActors());
            existingMovie.setImdbScore(updatedMovie.getImdbScore());
            existingMovie.setReleaseYear(updatedMovie.getReleaseYear());

            return movieRepository.save(existingMovie);
        } else {
            return null; // Movie with the given id not found
        }
    }

    public Movie deleteMovie(String id)
    {
        Optional<Movie> movieOptional = movieRepository.findById(id);

        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            movieRepository.deleteById(id);
            return movie;
        }
        else {
            // Movie not found, throw an exception or return a special value
            throw new MovieNotFoundException("Movie not found with ID: " + id);
        }
    }

    public List<Movie> getMovieByDirector(String director) {
        return movieRepository.findByDirector(director);
    }
}
