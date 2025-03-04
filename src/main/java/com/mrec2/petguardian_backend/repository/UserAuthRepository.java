package com.mrec2.petguardian_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mrec2.petguardian_backend.models.UserAuth;
import java.util.Optional;

@Repository
public interface UserAuthRepository extends MongoRepository<UserAuth, String> {
    Optional<UserAuth> findByEmail(String email); 
}
