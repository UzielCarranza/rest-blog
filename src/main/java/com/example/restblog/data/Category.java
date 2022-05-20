//package com.example.restblog.data;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import java.util.Collection;
//import java.util.List;
//
//public class Category {
//
//    private Long id;
//    private String name;
//
//
//    // many to many relationship with Posts
//    @JsonIgnoreProperties("posts")
//    private List<Post> posts;
//
//    public Category(Long id, String name, List<Post> posts) {
//        this.id = id;
//        this.name = name;
//        this.posts = posts;
//    }
//
//    public Category() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }
//}
