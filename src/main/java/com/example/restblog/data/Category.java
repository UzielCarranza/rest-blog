package com.example.restblog.data;

import java.util.Collection;

public class Category {

    private Long id;
    private String name;

    private Collection<Post> posts;

    public Category(Long id, String name, Collection<Post> posts) {
        this.id = id;
        this.name = name;
        this.posts = posts;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
