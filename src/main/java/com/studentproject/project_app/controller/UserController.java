package com.studentproject.project_app.controller;

import com.studentproject.project_app.domain.User;
import com.studentproject.project_app.manager.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private PollManager pollManager;

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        pollManager.addUser(user);
        return user;
    }

    // Get all users
    @GetMapping
    public Map<String, User> getAllUsers() {
        return pollManager.getUsers();
    }

    // Get a user by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return pollManager.getUser(username);
    }
}