package com.project.spring.controller;

import com.project.spring.entity.User;
import com.project.spring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/id")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<User> getUserById(@PathVariable Long customerId) {
        return ResponseEntity.ok(userService.getUserById(customerId));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<User> updateUser(@PathVariable Long customerId, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(customerId, user));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long customerId) {
        userService.deleteUser(customerId);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
