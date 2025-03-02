package com.mrec2.petguardian_backend.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Usuario { // NO es una entidad, solo un modelo de datos

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Email(message = "El correo debe ser válido")
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    // Constructor vacío (obligatorio para Spring)
    public Usuario() {
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

 
}
