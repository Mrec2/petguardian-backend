package com.mrec2.petguardian_backend.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mrec2.petguardian_backend.models.Post;
import com.mrec2.petguardian_backend.service.PostService;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Map<String, Object> requestBody) {
        String title = (String) requestBody.get("title");
        String content = (String) requestBody.get("content");
        String name = (String) requestBody.get("author");

        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Post post = new Post(name, title, content);
        Post savedPost = postService.createPost(post);
        return ResponseEntity.ok(savedPost);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllPosts() {
        List<Post> allPosts = postService.getAllPosts();

        List<Map<String, Object>> transformedPosts = allPosts.stream()
            .filter(post -> post.getName() != null && !post.getName().trim().isEmpty())
            .filter(post -> post.getTitle() != null && !post.getTitle().trim().isEmpty())
            .filter(post -> post.getContent() != null && !post.getContent().trim().isEmpty())
            .map(post -> {
                Map<String, Object> postMap = new HashMap<>();
                postMap.put("title", post.getTitle());
                postMap.put("content", post.getContent());
                postMap.put("author", post.getName()); 
                postMap.put("createdAt", post.getCreatedAt());
                return postMap;
            })
            .peek(postMap -> System.out.println(postMap))
            
            .collect(Collectors.toList());

        return ResponseEntity.ok(transformedPosts);
    }
}


/*package com.mrec2.petguardian_backend.controller;

import org.springframework.web.bind.annotation.*;
import com.mrec2.petguardian_backend.models.Post;
import com.mrec2.petguardian_backend.models.UserPosts;
import com.mrec2.petguardian_backend.repository.UserPostsRepository;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/posts")
public class PostController {
    private final UserPostsRepository userPostsRepository;

    public PostController(UserPostsRepository userPostsRepository) {
        this.userPostsRepository = userPostsRepository;
    }

    @PostMapping
    public ResponseEntity<UserPosts> addPostToUser(@RequestBody Map<String, Object> requestBody) {
        String title = (String) requestBody.get("title");
        String content = (String) requestBody.get("content");
        String name = (String) requestBody.get("author");

        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<UserPosts> userPostsOpt = userPostsRepository.findByName(name);
        UserPosts userPosts;

        if (userPostsOpt.isPresent()) {
            userPosts = userPostsOpt.get();
        } else {
            userPosts = new UserPosts(name);
        }

        userPosts.addPost(new Post(title, content));
        userPostsRepository.save(userPosts);

        return ResponseEntity.ok(userPosts);
    }

    @GetMapping
    public ResponseEntity<List<UserPosts>> getAllUserPosts() {
        return ResponseEntity.ok(userPostsRepository.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserPosts> getUserPosts(@PathVariable String name) {
        Optional<UserPosts> userPostsOpt = userPostsRepository.findByName(name);

        return userPostsOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}   */