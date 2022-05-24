package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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
        return getAll().stream().filter((post) -> {
                    return post.getId() == id;
                }).findFirst()
                .orElse(null);

    }


    @PostMapping
    private void createPost(@RequestBody Post newPost) {

        System.out.println(newPost);

    }


    @PutMapping("{id}")
    private void updatePost(@RequestBody Post updatedPost, @PathVariable Long id) {
        System.out.println(updatedPost);
        System.out.println(id);
        List<Post> postListUpdated = getAll();
        Post postToUpdate = getAll().stream().filter((post) -> {
                    return post.getId() == id;
                }).findFirst()
                .orElse(null);
        postListUpdated.set((int) postToUpdate.getId(), updatedPost);
        System.out.println(postListUpdated);

    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) {

        List<Post> postListDeleted = getAll();
        Post postToDelete = getAll().stream().filter((post) -> {
                    return post.getId() == id;
                }).findFirst()
                .orElse(null);
        postListDeleted.remove((int) postToDelete.getId());
        System.out.println(postListDeleted);
    }

}
