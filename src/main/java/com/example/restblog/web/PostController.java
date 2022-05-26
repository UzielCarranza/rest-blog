package com.example.restblog.web;

import com.example.restblog.DTO.CreatePostDto;
import com.example.restblog.data.Post;
import com.example.restblog.service.EmailService;
import com.example.restblog.service.PostService;
import com.example.restblog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostController {

    private final EmailService emailService;

    private final PostService postService;

    public PostController(EmailService emailService, PostService postService) {
        this.emailService = emailService;
        this.postService = postService;
    }
//    email service

    @GetMapping()
    public List<Post> getAll() {
        return postService.getAllPosts();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable Long id) {
        return postService.getAllPosts().stream().filter((post) -> {
                    return post.getId() == id;
                }).findFirst()
                .orElse(null);

    }


    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        System.out.println(newPost);
    }


    @PostMapping("{username}")
    public void createByUsername(@PathVariable String username, @RequestBody CreatePostDto dto) {
        Post newPost = new Post();
        postService.addPost(dto, newPost, username);
        emailService.prepareAndSend(newPost, "New Post","Ypu have created a new post");
    }


    @PutMapping("{id}")
    private void updatePost(@RequestBody Post updatedPost, @PathVariable Long id) {

        postService.updatePost(id, updatedPost);

    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        postService.deletePostById(id);
    }

    public List<Post> searchPosts(@RequestParam String keyword){
        return postService.getPostsByTitleKeyword(keyword);
    }

}
