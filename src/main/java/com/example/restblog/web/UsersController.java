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


    //    CREATE METHODS
    @PostMapping
    private void createUser(@RequestBody User newUser) {
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

    @GetMapping("email")
    public User getByEmail(@RequestParam("email") String email) {
        return userService.getUserByEmail(email);

    }

//


    //    DELETE METHODS
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) {
    }
//


    //    UPDATE METHODS
    @PutMapping("{id}/updatePassword")
    private void updatePassword(
            @PathVariable Long id, @RequestParam(required = false) String oldPassword,
            @Valid @Size(min = 3) @RequestParam String newPassword
    ) {
        User userToUpdate = getById(id);
        userToUpdate.setPassword(newPassword);
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