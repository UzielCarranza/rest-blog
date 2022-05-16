package com.example.restblog.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {


    @RequestMapping({"/", "/about", "/posts", "/login", "/home"})
    public String showView() {
        return "forward:/index.html";
    }

}
