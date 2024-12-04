package com.frenesie.collectif.service;

import com.frenesie.collectif.controller.dto.RegistrationDto;
import com.frenesie.collectif.model.User;
import com.frenesie.collectif.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public User authenticate(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
        
        return user;
    }
    
    @Transactional
    public void register(RegistrationDto dto) {
        // Vérifie si l'email existe déjà dans la base de données
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("L'email est déjà utilisé.");
        }

        // Créer l'utilisateur avec un rôle "USER" par défaut
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword())) // Encode password here
                .role("USER") // Ajout explicite du rôle
                .build();

        // Enregistrer l'utilisateur dans la base de données
        userRepository.save(user);
    }

    // Méthode pour vérifier si un utilisateur existe déjà par son email
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Méthode pour retrouver un utilisateur par son email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec cet email."));
    }

    // Autres méthodes de gestion des utilisateurs peuvent être ajoutées ici
}
