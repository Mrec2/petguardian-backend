package com.mrec2.petguardian_backend.config;

import com.mrec2.petguardian_backend.security.JwtAuthFilter;
import com.mrec2.petguardian_backend.security.JwtUtil;
import com.mrec2.petguardian_backend.security.UserDetailsServiceImpl;
import com.mrec2.petguardian_backend.repository.UserAuthRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(UserAuthRepository userAuthRepository) {
        return new UserDetailsServiceImpl(userAuthRepository);
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        return new JwtAuthFilter(jwtUtil, userDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF (para APIs REST)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login", "/api/users/register", "/api/users/users", "/auth/validate").permitAll() 
                .anyRequest().authenticated() 
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); 

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
