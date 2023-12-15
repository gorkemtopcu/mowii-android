package com.mowii.mowii.controller;


import com.mowii.mowii.exception.MovieNotFoundException;
import com.mowii.mowii.exception.UserNotFoundException;
import com.mowii.mowii.model.AuthenticationRequest;
import com.mowii.mowii.model.Movie;
import com.mowii.mowii.model.User;
import com.mowii.mowii.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            User user = userService.getById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    private List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) {
        try {
            User user = userService.getByEmail(request.getEmail());

            // Check if the provided password matches the stored password
            boolean isValid = user.getPassword().equals(request.getPassword());

            if (isValid) {
                return new ResponseEntity<>("Authentication successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.create(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            User deletedUser = userService.delete(id);
            return new ResponseEntity<>(deletedUser, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }
}
