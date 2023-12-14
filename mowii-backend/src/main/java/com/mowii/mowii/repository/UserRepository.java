package com.mowii.mowii.repository;

import com.mowii.mowii.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface UserRepository extends MongoRepository<User, String> {
    public List<User> findByName(String name);

}
