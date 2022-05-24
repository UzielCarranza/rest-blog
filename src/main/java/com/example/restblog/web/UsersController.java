package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;


@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()

    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUserById(id);

    }

    @PostMapping
    private void createUser(@RequestBody User newUser) {
        userService.getAllUsers().add(newUser);
    }


//    @PutMapping("{id}")
//    private void updateUser(@RequestBody User updatedUser, @PathVariable Long id) {
//        List<User> userListUpdated = getAll();
//        User userToUpdate = getAll().stream().filter((user) -> {
//                    return user.getId() == id;
//                }).findFirst()
//                .orElse(null);
//        userListUpdated.set((int) userToUpdate.getId(), updatedUser);
//        System.out.println(userListUpdated);
//
//    }

    @PostMapping("{username}")
    public void addUserPost(@PathVariable String username, @RequestBody Post newPost) {
        User user = userService.getUserByUsername(username);
        user.getPosts().add(newPost);
    }

    @GetMapping("username")
    public User getByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("email")
    public void getByEmail(@RequestParam("email") String email) {
        System.out.println("working on this feature");

    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) {

    }

    @PutMapping("{id}/updatePassword")
    private void updatePassword(
            @PathVariable Long id, @RequestParam(required = false) String oldPassword,
            @Valid @Size(min = 3) @RequestParam String newPassword
    ) {
        User userToUpdate = userService.getUserById(id);
        userToUpdate.setPassword(newPassword);
    }
}