package com.frenesie.collectif.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @Size(min = 3, message = "Le nom d'utilisateur doit contenir au moins 3 caractères")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Le rôle est obligatoire")
    private String role;

    // Constructeur sans argument explicite
    public User() {
        this.role = "USER";  // Valeur par défaut pour le rôle
    }
    
    // Constructor with arguments (explicit)
    public User(Integer id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = (role != null) ? role : "USER";  // Définit un rôle par défaut si aucun rôle n'est donné
    }

    // Méthode pour hacher le mot de passe avant de l'enregistrer
    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password); // Mot de passe haché avant de le stocker
    }
}
