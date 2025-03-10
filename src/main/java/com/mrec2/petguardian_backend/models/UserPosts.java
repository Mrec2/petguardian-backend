package com.mrec2.petguardian_backend.models;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "data")
public class UserPosts {

    private String name;
    private List<Post> posts;

    public UserPosts(String name) {
        this.name = name;
        this.posts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
