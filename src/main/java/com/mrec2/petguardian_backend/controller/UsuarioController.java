package com.mrec2.petguardian_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrec2.petguardian_backend.models.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="*")
public class UsuarioController {

	@PostMapping("/register")
	public ResponseEntity<String> registrarUsuario (@Valid @RequestBody Usuario usuario ){
		 System.out.println("📩 Nuevo usuario registrado:");
	        System.out.println("Nombre: " + usuario.getName());
	        System.out.println("Correo: " + usuario.getEmail());
	        System.out.println("Contraseña: " + usuario.getPassword());
	        return ResponseEntity.ok("Datos recibidos correctamente");
	}
	
}
