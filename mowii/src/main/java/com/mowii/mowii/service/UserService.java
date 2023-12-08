package com.mowii.mowii.service;

import com.mowii.mowii.exception.UserNotFoundException;
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

    public User deleteUser(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.deleteById(id);
            return user;
        }
        else {
            // User not found, throw an exception or return a special value
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }
}
