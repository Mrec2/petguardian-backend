package com.mrec2.petguardian_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrec2.petguardian_backend.models.User;
import com.mrec2.petguardian_backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser (User user) {
        return userRepository.save(user);
    }
}
