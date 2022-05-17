package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {


    @GetMapping()
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setEmail("email@email");
        user.setCreatedAt(new Date(2022, 12, 21));
        user.setRole(User.Role.USER);
        user.setPassword("daeadae");


        User user2 = new User();
        user2.setId(2);
        user2.setEmail("email@email");
        user2.setCreatedAt(new Date(2022, 12, 21));
        user2.setRole(User.Role.USER);
        user2.setPassword("eadae");
        users.add(user);
        users.add(user2);

        return users;
    }
}
