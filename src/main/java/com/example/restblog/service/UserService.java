package com.example.restblog.service;

import com.example.restblog.DTO.CreatePostDto;
import com.example.restblog.data.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final CategoryRepository categoryRepository;

    public UserService(UserRepository userRepository, PostsRepository postsRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public List<Post> getAllPosts() {

        return postsRepository.findAll();
    }
    public void createUser(User newUser){
        userRepository.save(newUser);
    }

    public void addPost(CreatePostDto dto, Post newPost, String username) {
        User user = getUserByUsername(username);
        user.getPosts().add(newPost);
        newPost.setTitle(dto.getTitle());
        newPost.setContent(dto.getContent());

        newPost.setUser(user);
        List<Category> categoriesToAdd = new ArrayList<>();
        for (String category : dto.getCategories()) {
            categoriesToAdd.add(categoryRepository.findCategoryByName(category));
        }
        newPost.setCategories(categoriesToAdd);

        postsRepository.save(newPost);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
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

    public void deletePostById(Long id) {
        postsRepository.deleteById(id);
    }

    public List<Post> getPostsByTitleKeyword(String keyword) {
        return postsRepository.searchByTiTleLike(keyword);
    }
}
