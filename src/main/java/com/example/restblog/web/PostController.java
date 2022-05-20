package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostController {


    @GetMapping()
    public List<Post> getAll() {
        List<Post> postList = new ArrayList<>();
        List<User> users = new ArrayList<>();
//        List<Category> category = new ArrayList<>();
//        category.add(new Category(1L, "horros", postList));
        User user1 = new User(3L, "GranReux", "@email1", "1234", postList);
        Post post1 = new Post(3, "Elon Musk Tweet", "He just invested into this coin....", user1);
        postList.add(post1);

        User use3r2 = new User(1L, "TheDeux", "some@ameil", "21211", postList);
        Post post2 = new Post(1, "War in....", "Crazy how this war is going...", use3r2);

        User user3 = new User(2L, "TheLiberal21","sasa@adadea", "ads",postList);
        Post post3 = new Post(2, "Elections", "Who's gonna win?", user3);

        postList.add(post2);
        postList.add(post3);
        users.add(user1);
        users.add(use3r2);
        users.add(user3);
//        postList.add(new Post(1, "War in....", "Crazy how this war is going..."));
//
//        postList.add(new Post(2, "Elections", "Who's gonna win?"));
        return postList;
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
