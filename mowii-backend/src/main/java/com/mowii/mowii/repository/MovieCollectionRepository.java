package com.mowii.mowii.repository;

import com.mowii.mowii.model.MovieCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MovieCollectionRepository extends MongoRepository<MovieCollection, String> {
    Optional<List<MovieCollection>> findAllByUserId(String userId);
}
