package com.example.restblog.service;

import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.UserRepository;

public class UserService {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    public UserService(UserRepository userRepository, PostsRepository postsRepository) {
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
    }



}
