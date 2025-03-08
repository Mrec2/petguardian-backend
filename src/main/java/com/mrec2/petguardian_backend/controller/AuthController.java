package com.mrec2.petguardian_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mrec2.petguardian_backend.security.JwtUtil;
import com.mrec2.petguardian_backend.service.UserAuthService;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private JwtUtil jwtUtil; // Agregamos JwtUtil para manejar tokens

    // 🔹 Login y generación de token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        String token = userAuthService.login(email, password);

        if (token != null) {
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }

    // 🔹 Validar el Token (Desde el frontend)
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(Map.of("valid", false, "message", "Token no proporcionado"));
        }

        String token = authHeader.substring(7); // Remover "Bearer "
        try {
            String email = jwtUtil.extractEmail(token);

            if (jwtUtil.validateToken(token, email)) {
                return ResponseEntity.ok(Map.of("valid", true, "email", email));
            } else {
                return ResponseEntity.status(401).body(Map.of("valid", false, "message", "Token inválido o expirado"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("valid", false, "message", "Token no válido"));
        }
    }
}
