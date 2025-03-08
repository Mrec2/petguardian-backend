package com.mrec2.petguardian_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mrec2.petguardian_backend.models.UserAuth;
import com.mrec2.petguardian_backend.repository.UserAuthRepository;
import com.mrec2.petguardian_backend.security.JwtUtil;

import java.util.Optional;

@Service
public class UserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String email, String password) {
        Optional<UserAuth> userOptional = userAuthRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            UserAuth user = userOptional.get();
            // Verificar la contraseña encriptada
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Si la contraseña es correcta, generar un token JWT
                return jwtUtil.generateToken(email);
            }
        }
        return null; // Si las credenciales no son válidas, devolver null
    }
}
