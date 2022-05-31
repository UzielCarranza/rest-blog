package com.example.restblog.service;


import com.example.restblog.DTO.CreatePostDto;
import com.example.restblog.data.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    //    GET METHODS
    public List<Post> getAllPosts() {

        return postsRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postsRepository.findById(postId).orElseThrow();

    }

    public List<Post> getPostsByTitleKeyword(String keyword) {
        return postsRepository.searchByTiTleLike(keyword);
    }

    //


    //    CREATE METHODS
    public void addPost(CreatePostDto dto, Post newPost, String email) {
        User user = userService.getUserByEmail(email);
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
//


//    UPDATE METHODS

    public void updatePost(CreatePostDto dto, Long postId, Post post) {
        Post postToUpdated = postsRepository.findById(postId).orElseThrow();

        if (dto.getContent() != null && !dto.getContent().isEmpty()) {
            postToUpdated.setContent(dto.getContent());
        }
        if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
            postToUpdated.setTitle(dto.getTitle());
        }
        postsRepository.save(postToUpdated);
    }
//


    //    DELETE METHODS
    public void deletePostById(Long id) {
        postsRepository.deleteById(id);
    }
//
}
