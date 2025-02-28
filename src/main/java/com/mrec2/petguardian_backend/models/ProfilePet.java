package com.mrec2.petguardian_backend.models;

public class ProfilePet {

	private String ownerPetName;
	private String petType;
	private String description;
	
	public ProfilePet(String ownerPetName, String petType, String description) {
		this.description = description;
		this.petType = petType;
		this.ownerPetName = ownerPetName;
	}

	public String getOwnerPetName() {
		return ownerPetName;
	}

	public void setOwnerPetName(String ownerPetName) {
		this.ownerPetName = ownerPetName;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProfilePet [Dueño=" + ownerPetName + ", Raza=" + petType + ", Descripcion=" + description
				+ "]";
	}
	
	
	
}
