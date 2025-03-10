// package com.mrec2.petguardian_backend.models;

// import java.time.LocalDateTime;

// public class Post {
//     private String title;
//     private String content;
//     private LocalDateTime createdAt;

//     public Post(String title, String content) {
//         this.title = title;
//         this.content = content;
//         this.createdAt = LocalDateTime.now();
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }

//     public String getContent() {
//         return content;
//     }

//     public void setContent(String content) {
//         this.content = content;
//     }

//     public LocalDateTime getCreatedAt() {
//         return createdAt;
//     }

//     public void setCreatedAt(LocalDateTime createdAt) {
//         this.createdAt = createdAt;
//     }
// }














package com.mrec2.petguardian_backend.models;

import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "data")
public class Post {

    private String name;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public Post(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Post [name=" + name + ", title=" + title + ", content=" + content + ", createdAt=" + createdAt + "]";
    }
}
