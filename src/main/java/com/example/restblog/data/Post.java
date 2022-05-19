package com.example.restblog.data;


import java.util.Collection;

public class Post {
    private long id;
    private String title;
    private String content;

    private User user;


// many to many relationship with Categories
    private Collection<Category> categories;

    public Post(long id, String title, String content, User user, Collection<Category> categories) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.categories = categories;
    }

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

//    User


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //


    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", categories=" + categories +
                '}';
    }
}
