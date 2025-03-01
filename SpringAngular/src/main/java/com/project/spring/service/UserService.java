package com.project.spring.service;

import com.project.spring.entity.User;
import com.project.spring.exception.ResourceNotFoundException;
import com.project.spring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long customerId) {
        return userRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + customerId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long customerId, User userDetails) {
        User user = userRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + customerId));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());

        return userRepository.save(user);
    }

    public void deleteUser(Long customerId) {
        User user = userRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with customerId: " + customerId));
        userRepository.delete(user);
    }
}
