package com.example.QuoraApp.controller;


import com.example.QuoraApp.models.User;
import com.example.QuoraApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public User registerUser(@RequestBody User user) {
        return userService.rigisterUser(user);
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/user/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.getUserById(id)
                .map(user -> {
                    System.out.println(user + "---------++++++++");
                    user.setUserName(userDetails.getUserName());
                    user.setEmail(userDetails.getEmail());
                    return userService.saveUser(user);
                });
    }

}
