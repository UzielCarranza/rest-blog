package com.example.restblog.service;


import com.example.restblog.DTO.CreatePostDto;
import com.example.restblog.data.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {


    private final PostsRepository postsRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;


    public PostService(PostsRepository postsRepository, CategoryRepository categoryRepository, UserService userService) {
        this.postsRepository = postsRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts() {

        return postsRepository.findAll();
    }

    public void addPost(CreatePostDto dto, Post newPost, String username) {
        User user = userService.getUserByUsername(username);
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