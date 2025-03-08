package com.mrec2.petguardian_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.mrec2.petguardian_backend.models.UserAuth;
import com.mrec2.petguardian_backend.repository.UserAuthRepository;
import java.util.Optional;

@Service
public class UserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; 

    public boolean login(String email, String password) {
        Optional<UserAuth> userOptional = userAuthRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            UserAuth user = userOptional.get();
            
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
