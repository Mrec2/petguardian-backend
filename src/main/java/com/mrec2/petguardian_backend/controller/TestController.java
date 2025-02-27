package com.mrec2.petguardian_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

	@GetMapping("/message")
	public String getMessage() {
		return "Hello from PetGuardian backend side";
	}
	
}
