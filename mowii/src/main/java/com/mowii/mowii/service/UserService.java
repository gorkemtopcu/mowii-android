package com.mowii.mowii.service;

import com.mowii.mowii.repository.UserRepository;
import com.mowii.mowii.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Add any additional business logic here if needed
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String userId) {
        return  userRepository.findById(userId);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
