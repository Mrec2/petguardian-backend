package com.mrec2.petguardian_backend.security;

import com.mrec2.petguardian_backend.models.UserAuth;
import com.mrec2.petguardian_backend.repository.UserAuthRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    public UserDetailsServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserAuth> userOptional = userAuthRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
        }

        UserAuth user = userOptional.get();

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // Spring Security maneja la validación de contraseñas
                .roles("USER") // Puedes cambiar esto según tu sistema de roles
                .build();
    }
}
