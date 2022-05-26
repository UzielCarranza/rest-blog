package com.example.restblog.service;

import com.example.restblog.data.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CREATE METHODS
    public void createUser(User newUser) {
        userRepository.save(newUser);
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    //


    // GET METHODS
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
//


    //    UPDATE METHODS
    public void updateEmail(Long userId, String newEmail) {
        User user = getUserById(userId);
        user.setEmail(newEmail);
        userRepository.save(user);
    }

}
