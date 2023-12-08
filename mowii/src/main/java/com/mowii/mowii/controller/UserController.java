package com.mowii.mowii.controller;


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

    @GetMapping("/getAll")
    private List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestBody String id) {
        User deletedUser = userService.deleteUser(id);
        return new ResponseEntity<>(deletedUser, deletedUser != null ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
    }
}
