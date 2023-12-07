package com.mowii.mowii.repository;

import com.mowii.mowii.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
}