package com.frenesie.collectif.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity	
@Table(name = "users")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @Size(min = 3, message = "Le nom d'utilisateur doit contenir au moins 3 caractères")
    private String username;

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(nullable = false)
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password;

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(unique = true, nullable = false)
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
    private boolean actif = true;
   

    @Enumerated(EnumType.STRING)  // Spécifie que l'énumération sera stockée sous forme de chaîne de caractères
    private Role role;  // On suppose que Role est l'énumération de rôles

    public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public enum Role {
        UTILISATEUR, 
        ADMIN,
        ARTISTE
    }
    
    //S'assurer que la classe Role existe bien et qu'elle contient les bons attributs pour définir les rôles (par exemple, ROLE_ADMIN, ROLE_USER, ROLE_ARTISTE)
    //Ajouter des méthodes pour vérifier les rôles dans Utilisateur pour simplifier l'accès aux autorisations dans le code

    
}
