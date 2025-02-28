package com.mrec2.petguardian_backend.service;

import org.springframework.stereotype.Service;

import com.mrec2.petguardian_backend.models.ProfilePet;

@Service
public class TestService {

	private ProfilePet profilePet;
	
	public String getProfilePet () {
		profilePet = new ProfilePet("Mario", "Yorkshire", "Perro perdido en la calle Salomé nº 5");
		System.out.println(profilePet.toString());
		return profilePet.toString();
	}
	
}
