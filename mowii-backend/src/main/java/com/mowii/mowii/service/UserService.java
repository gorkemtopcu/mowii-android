package com.mowii.mowii.service;

import com.mowii.mowii.exception.UserNotFoundException;
import com.mowii.mowii.repository.UserRepository;
import com.mowii.mowii.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements MowiiService<User> {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        // Add any additional business logic here if needed
        return userRepository.save(user);
    }

    @Override
    public User getById(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            // User not found, throw an exception or return a special value
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


    @Override
    public User update(String id, User updatedUser) {
        User user = getById(id);

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

        return userRepository.save(user);
    }

    @Override
    public User delete(String id) {
        User user = getById(id);
        userRepository.deleteById(id);
        return user;
    }
}
