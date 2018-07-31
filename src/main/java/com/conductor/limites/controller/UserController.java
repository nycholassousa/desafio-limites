package com.conductor.limites.controller;

import com.conductor.limites.exceptions.ResourceNotFoundException;
import com.conductor.limites.models.User;
import com.conductor.limites.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    //Get All Users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Create a new User
    @PostMapping("/users/new")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    //Get a single User
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    //Update an User - Only Password
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") long userId, @Valid @RequestBody User details) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setPassword(details.getPassword());
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    //Delete an User
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
}
