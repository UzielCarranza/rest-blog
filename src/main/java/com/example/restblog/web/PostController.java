package com.example.restblog.web;


import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostController {


    @GetMapping("/api/posts")
    public List<Post> getAll() {
        List<Post> postList = new ArrayList<>();
        postList.add(new Post(0, "post1", "this is a comment"));

        postList.add(new Post(1, "post2", "this is a comment"));

        postList.add(new Post(2, "post2", "this is a comment"));
        return postList;
    }

}
