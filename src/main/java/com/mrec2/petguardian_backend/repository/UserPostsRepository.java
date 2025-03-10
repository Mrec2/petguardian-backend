package com.mrec2.petguardian_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mrec2.petguardian_backend.models.UserPosts;
import java.util.Optional;

public interface UserPostsRepository extends MongoRepository<UserPosts, String> {
    Optional<UserPosts> findByName(String name);
}
