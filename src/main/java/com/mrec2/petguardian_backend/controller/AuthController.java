package com.mrec2.petguardian_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mrec2.petguardian_backend.models.User;
import com.mrec2.petguardian_backend.security.JwtUtil;
import com.mrec2.petguardian_backend.service.UserAuthService;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private JwtUtil jwtUtil; 

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        
        String token = userAuthService.login(email, password);

        if (token != null) {
            
            Optional<User> userOptional = userAuthService.getUserByEmail(email);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return ResponseEntity.ok(Map.of(
                    "token", token,
                    "name", user.getName() // ✅ Ahora enviamos el nombre desde User
                ));
            }
        }

        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(Map.of("valid", false, "message", "Token no proporcionado"));
        }

        String token = authHeader.substring(7); // Remover "Bearer "
        try {
            String email = jwtUtil.extractEmail(token);

            if (jwtUtil.validateToken(token, email)) {
                Optional<User> userOptional = userAuthService.getUserByEmail(email);
                if (userOptional.isPresent()) {
                    return ResponseEntity.ok(Map.of(
                        "valid", true,
                        "email", email,
                        "name", userOptional.get().getName() // ✅ Enviamos también el nombre
                    ));
                }
            }
            return ResponseEntity.status(401).body(Map.of("valid", false, "message", "Token inválido o expirado"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("valid", false, "message", "Token no válido"));
        }
    }
}
