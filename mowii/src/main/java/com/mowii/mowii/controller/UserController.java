package com.mowii.mowii.controller;


import com.mowii.mowii.model.User;
import com.mowii.mowii.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/greet")
    public String sayHello() {

        return "Hello user, Spring Rest";

    }

    @PostMapping("/post")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
