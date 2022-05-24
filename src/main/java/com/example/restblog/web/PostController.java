package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostController {

    private final UserService userService;

    public PostController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<Post> getAll() {
        return userService.getAllPosts();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable Long id) {
        return userService.getAllPosts().stream().filter((post) -> {
                    return post.getId() == id;
                }).findFirst()
                .orElse(null);

    }


    @PostMapping
    private void createPost(@RequestBody Post newPost) {

        System.out.println(newPost);

    }


    @PostMapping("{username}")
    public void createByUsername(@PathVariable String username, @RequestBody Post newPost){
        // Nice and clean, huh?
        userService.addPost(newPost, username);
    }


    @PutMapping("{id}")
    private void updatePost(@RequestBody Post updatedPost, @PathVariable Long id) {

        userService.updatePost(id, updatedPost);

    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deletePostById(id);
    }

}
