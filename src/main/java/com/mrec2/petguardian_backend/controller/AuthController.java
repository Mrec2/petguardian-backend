package com.mrec2.petguardian_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mrec2.petguardian_backend.service.UserAuthService;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        String token = userAuthService.login(email, password);

        if (token != null) {
            return ResponseEntity.ok(Map.of("token", token)); // Devuelve el token en JSON
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
