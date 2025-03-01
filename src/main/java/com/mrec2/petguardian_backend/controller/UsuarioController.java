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
@RequestMapping("/api/usuarios")
@CrossOrigin(origins="*")
public class UsuarioController {

	@PostMapping("/registro")
	public ResponseEntity<String> registrarUsuario (@Valid @RequestBody Usuario usuario ){
		 System.out.println("📩 Nuevo usuario registrado:");
	        System.out.println("Nombre: " + usuario.getNombre());
	        System.out.println("Correo: " + usuario.getCorreo());
	        System.out.println("Contraseña: " + usuario.getContraseña());
	        return ResponseEntity.ok("Datos recibidos correctamente");
	}
	
}
