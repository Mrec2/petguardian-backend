package com.mrec2.petguardian_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mrec2.petguardian_backend.service.TestService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class TestController {

	@Autowired
	TestService testService;
	
	@GetMapping("/message")
	public ResponseEntity<String> getMessage() {
		String result = testService.getProfilePet();
		return ResponseEntity.ok().body(result);
	}
	
}
