package com.example.restblog.web;

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

    @PatchMapping("{id}/updatePassword")
    private void updatePassword(
            @PathVariable Long id, @RequestParam(required = false) String oldPassword,
            @Valid @Size(min = 3) @RequestParam String newPassword
    ) {
        List<User> userUpdate = getAll();
        User updatePassword = getAll().stream().filter((user) -> {
                    return user.getId() == id;
                }).findFirst()
                .orElse(null);
        if (updatePassword.getPassword().equals(oldPassword) && (newPassword.length() > 3)) {
            updatePassword.setPassword(newPassword);
            System.out.println(updatePassword);
            System.out.println("password updated");
        } else {
            System.out.println("not the same");
        }
    }
}