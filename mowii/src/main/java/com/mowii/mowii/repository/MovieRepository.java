package com.mowii.mowii.repository;

import com.mowii.mowii.model.Movie;
import com.mowii.mowii.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {
    public List<Movie> findByName(String name);
    public List<Movie> findByDirector(String name);
    public List<Movie> findByActor(String name);
}