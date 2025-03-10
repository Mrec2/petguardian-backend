package com.mrec2.petguardian_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.mrec2.petguardian_backend.models.Post;

public interface PostRepository extends MongoRepository <Post, String> {

}
