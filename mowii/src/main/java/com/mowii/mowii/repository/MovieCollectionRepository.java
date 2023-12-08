package com.mowii.mowii.repository;

import com.mowii.mowii.model.MovieCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieCollectionRepository extends MongoRepository<MovieCollectionRepository, String> {
    public List<MovieCollection> findByUser(String name);
}
