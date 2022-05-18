package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {


    @GetMapping()
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(0);
        user.setEmail("email@email");
        user.setPassword("daeadae");
        user.setUsername("one");

        User user2 = new User();
        user2.setId(1);
        user2.setEmail("email@email");
        user2.setPassword("eadae");
        user2.setUsername("dae");
        users.add(user);
        users.add(user2);

        return users;
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        return getAll().stream().filter((user) -> {
                    return user.getId() == id;
                }).findFirst()
                .orElse(null);

    }

    @PostMapping
    private void createUser(@RequestBody User newUser) {

        System.out.println(newUser);

    }


    @PutMapping("{id}")
    private void updateUser(@RequestBody User updatedUser, @PathVariable Long id) {
        List<User> userListUpdated = getAll();
        User userToUpdate = getAll().stream().filter((user) -> {
                    return user.getId() == id;
                }).findFirst()
                .orElse(null);
        userListUpdated.set((int) userToUpdate.getId(), updatedUser);
        System.out.println(userListUpdated);

    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) {

        List<User> UserListDeleted = getAll();
        User UserToDelete = getAll().stream().filter((user) -> {
                    return user.getId() == id;
                }).findFirst()
                .orElse(null);
        UserListDeleted.remove((int) UserToDelete.getId());
        System.out.println(UserListDeleted);
    }


    //    needs to return List<User>... for now is just printing the username for testing purposes
    @GetMapping("/username/{username}")
    public User getByUsername(@PathVariable("username") String username) {
        List<User> userByUsername = getAll();

        User getByUsername = getAll().stream().filter((user) -> {
                    return user.getUsername().contains(username);
                }).findFirst()
                .orElse(null);
        int userId = (int) getByUsername.getId();
        System.out.println(userId);
        System.out.println(userByUsername);
        return userByUsername.get(userId);
//        return getByUsername;

    }


    @GetMapping("/email")
    public void getByEmail(@RequestParam("email") String email) {
        List<User> userByEmail = getAll();

        User getByEmail = getAll().stream().filter((user) -> {
                    return user.getEmail().contains(email);
                }).findFirst()
                .orElse(null);
        System.out.println(getByEmail);
//        return getByUsername;

    }


}
