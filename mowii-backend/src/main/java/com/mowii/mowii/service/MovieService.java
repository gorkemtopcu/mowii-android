package com.mowii.mowii.service;

import com.mowii.mowii.exception.MovieNotFoundException;
import com.mowii.mowii.model.Movie;
import com.mowii.mowii.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements MowiiService<Movie>{

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getById(String id) {
        Optional<Movie> userOptional = movieRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new MovieNotFoundException("Movie not found with ID: " + id);
        }
    }

    public List<Movie> getMovieByDirector(String director) {
        return movieRepository.findByDirector(director);
    }

    public List<Movie> getMovieByRating(double rating) {
        return movieRepository.findByImdbScoreGreaterThanEqual(rating);
    }

    public List<Movie> getMovieByYear(Integer year) {
        return movieRepository.findByReleaseYear(year);
    }

    public List<Movie> getMovieByGenre(String genre) {
        return movieRepository.findByGenresContaining(genre);
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie update(String id, Movie updatedMovie) {
        Movie movie = getById(id);

        movie.setTitle(updatedMovie.getTitle());
        movie.setGenres(updatedMovie.getGenres());
        movie.setDirector(updatedMovie.getDirector());
        movie.setActors(updatedMovie.getActors());
        movie.setImdbScore(updatedMovie.getImdbScore());
        movie.setReleaseYear(updatedMovie.getReleaseYear());

        return movieRepository.save(movie);
    }

    @Override
    public Movie delete(String id) {
        Movie movie = getById(id);
        movieRepository.deleteById(id);
        return movie;
    }
}
