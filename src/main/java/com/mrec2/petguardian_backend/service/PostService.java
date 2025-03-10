package com.mrec2.petguardian_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrec2.petguardian_backend.models.Post;
import com.mrec2.petguardian_backend.repository.PostRepository;

@Service
public class PostService {
 @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
