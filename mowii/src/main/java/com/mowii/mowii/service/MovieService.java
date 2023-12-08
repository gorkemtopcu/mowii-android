package com.mowii.mowii.service;

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
            existingMovie.setGenre(updatedMovie.getGenre());
            existingMovie.setDirector(updatedMovie.getDirector());
            existingMovie.setActor(updatedMovie.getActor());
            existingMovie.setImdbScore(updatedMovie.getImdbScore());
            existingMovie.setReleaseYear(updatedMovie.getReleaseYear());

            return movieRepository.save(existingMovie);
        } else {
            return null; // Movie with the given id not found
        }
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }

    public boolean doesMovieExist(String id) {return movieRepository.existsById(id);
    }
}
