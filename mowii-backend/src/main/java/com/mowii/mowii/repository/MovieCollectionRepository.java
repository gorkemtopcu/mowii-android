package com.mowii.mowii.repository;

import com.mowii.mowii.model.MovieCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieCollectionRepository extends MongoRepository<MovieCollection, String> {
}
