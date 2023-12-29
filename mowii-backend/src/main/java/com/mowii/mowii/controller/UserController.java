package com.mowii.mowii.controller;


import com.mowii.mowii.exception.UserNotFoundException;
import com.mowii.mowii.model.AuthenticationRequest;
import com.mowii.mowii.model.User;
import com.mowii.mowii.service.MovieCollectionService;
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
    private final MovieCollectionService movieCollectionService;

    @Autowired
    public UserController(UserService userService, MovieCollectionService movieCollectionService) {
        this.userService = userService;
        this.movieCollectionService = movieCollectionService;
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

    @PostMapping("/check-email")
    public ResponseEntity<?> checkEmailAvailability(@RequestBody AuthenticationRequest request) {
        try {
            // Attempt to retrieve a user with the provided email
            User existingUser = userService.getByEmail(request.getEmail());

            // If the user is found, the email is already taken
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);

        } catch (UserNotFoundException e) {
            // If the user is not found, the email is available
            return new ResponseEntity<>("Email is available", HttpStatus.OK);
        }
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) {
        try {
            User user = userService.getByEmail(request.getEmail());

            // Check if the provided password matches the stored password
            boolean isValid = user.getPassword().equals(request.getPassword());

            if (isValid) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        boolean isEmailAvailable = checkEmailAvailability(new AuthenticationRequest(user.getEmail(), user.getPassword())).getStatusCode() == HttpStatus.OK;
        if (isEmailAvailable) {
            User createdUser = userService.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            //Delete all collections of the user
            User deletedUser = userService.delete(id);
            movieCollectionService.deleteCollectionsOfUser(id);

            return new ResponseEntity<>(deletedUser, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }
}
