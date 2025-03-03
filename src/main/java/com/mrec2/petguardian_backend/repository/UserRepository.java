package com.mrec2.petguardian_backend.repository;

import org.springframework.stereotype.Repository;
import com.mrec2.petguardian_backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
}
