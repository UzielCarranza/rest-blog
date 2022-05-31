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


    //    UPDATE METHODS
    public void updateEmail(Long userId, String newEmail) {
        User user = getUserById(userId);
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public void updateName(Long userId, String newName) {
        User userUpdatedName = getUserById(userId);
        userUpdatedName.setUsername(newName);
        userRepository.save(userUpdatedName);
    }

    public void updatePassword(Long userID, String newPass){
        User userToUpdatePass = getUserById(userID);
        userToUpdatePass.setPassword(newPass);
        userRepository.save(userToUpdatePass);
    }

//

//    DELETE METHODS
    public void deleteById(Long userId){
        userRepository.deleteById(userId);
    }

}
