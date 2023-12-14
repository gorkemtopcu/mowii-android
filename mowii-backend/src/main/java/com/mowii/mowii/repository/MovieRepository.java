package com.mowii.mowii.repository;

import com.mowii.mowii.model.Movie;
import com.mowii.mowii.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {


    List<Movie> findByDirector(String director);

    List<Movie> findByImdbScoreGreaterThanEqual(double imdbScore);

    List<Movie> findByReleaseYear(int year);

    List<Movie> findByGenresContaining(String genre);
}
