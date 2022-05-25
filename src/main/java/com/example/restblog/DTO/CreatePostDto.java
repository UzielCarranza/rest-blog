package com.example.restblog.DTO;

import java.util.List;

public class CreatePostDto {
    private String title;
    private String content;
    private List<String> categories;


    public CreatePostDto(){

    }

    public CreatePostDto(String title, String content, List<String> categories) {
        this.title = title;
        this.content = content;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
