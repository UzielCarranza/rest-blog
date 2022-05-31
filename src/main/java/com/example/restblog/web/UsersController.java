package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;


@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    public UsersController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    //    CREATE METHODS
    @PostMapping("create")
    private void createUser(@RequestBody User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userService.createUser(newUser);
    }

    @PostMapping("{username}")
    public void addUserPost(@PathVariable String username, @RequestBody Post newPost) {
        User user = userService.getUserByUsername(username);
        user.getPosts().add(newPost);
    }

//


//    GET METHODS

    @GetMapping()
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUserById(id);

    }

    @GetMapping("username")
    public User getByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

//


    //    DELETE METHODS
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
//


    //    UPDATE METHODS
    @PutMapping("{userId}/updatePassword")
    private void updatePassword(
            @PathVariable Long userId, @RequestParam(required = false) String oldPassword,
            @Valid @Size(min = 3) @RequestParam String newPassword
    ) {
        userService.updatePassword(userId, newPassword);
    }


    @PutMapping("{userId}/updateEmail")
    public void updateEmail(@PathVariable long userId, @RequestParam String newEmail) {
        userService.updateEmail(userId, newEmail);
    }

    @PutMapping("{userId}/updateName")
    public void updateName(@PathVariable Long userId, @RequestParam String newName) {
        userService.updateName(userId, newName);
    }
//

}