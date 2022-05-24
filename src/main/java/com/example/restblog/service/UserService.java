package com.example.restblog.service;

import com.example.restblog.data.Post;
import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.User;
import com.example.restblog.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    public UserService(UserRepository userRepository, PostsRepository postsRepository) {
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Post> getAllPosts() {
        return postsRepository.findAll();
    }

    public void addPost(Post newPost, String username) {
        User user = getUserByUsername(username);

        user.getPosts().add(newPost);
        newPost.setUser(user);
        postsRepository.save(newPost);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public void updatePost(Long postId, Post post) {
        Post postToUpdated = postsRepository.findById(postId).orElseThrow();

        if (post.getContent() != null && !post.getContent().isEmpty()) {
            postToUpdated.setContent(post.getContent());
        }
        if (post.getTitle() != null && !post.getTitle().isEmpty()) {
            postToUpdated.setTitle(post.getTitle());
        }
        postsRepository.save(postToUpdated);
    }
}
