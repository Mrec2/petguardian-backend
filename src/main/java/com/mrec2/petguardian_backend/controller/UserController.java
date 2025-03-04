package com.mrec2.petguardian_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrec2.petguardian_backend.models.User;
import com.mrec2.petguardian_backend.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="*")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser (@Valid @RequestBody User usuario ){
		//  System.out.println(" Nuevo usuario registrado:");
	    //     System.out.println("Nombre: " + usuario.getName());
	    //     System.out.println("Correo: " + usuario.getEmail());
	    //     System.out.println("Contraseña: " + usuario.getPassword());

	        userService.createUser(usuario);


	        return ResponseEntity.ok("Datos recibidos correctamente");
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	
}
