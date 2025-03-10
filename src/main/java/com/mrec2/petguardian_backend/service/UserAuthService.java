package com.mrec2.petguardian_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mrec2.petguardian_backend.models.UserAuth;
import com.mrec2.petguardian_backend.models.User;
import com.mrec2.petguardian_backend.repository.UserAuthRepository;
import com.mrec2.petguardian_backend.repository.UserRepository;
import com.mrec2.petguardian_backend.security.JwtUtil;

import java.util.Optional;

@Service
public class UserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String email, String password) {
        Optional<UserAuth> userAuthOptional = userAuthRepository.findByEmail(email);
        if (userAuthOptional.isPresent()) {
            UserAuth userAuth = userAuthOptional.get();
            if (passwordEncoder.matches(password, userAuth.getPassword())) {
                return jwtUtil.generateToken(email);
            }
        }
        return null;
    }

    
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
