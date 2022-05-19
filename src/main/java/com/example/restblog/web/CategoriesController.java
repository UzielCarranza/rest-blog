package com.example.restblog.web;


import com.example.restblog.data.Category;
import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/categories", headers = "Accept=application/json")
public class CategoriesController {


    @GetMapping
    private Category getPostByCategory() {
        List<Post> postListEample = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<Category> category = new ArrayList<>();
        category.add(new Category(1L, "horros", postListEample));
        User user1 = new User(3L, "tet1", "@email1", "1234", postListEample);
        Post post1 = new Post(3, "Elon Musk Tweet", "He just invested into this coin....", user1,
                category);
        users.add(user1);
        postListEample.add(post1);

        return category.get(0);
    }
}
