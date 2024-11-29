package com.frenesie.collectif.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Exemple : "USER", "ADMIN", "ARTISTE"

    public Role() {
        // Constructeur sans argument
    }

    public Role(String name) {
        this.name = name;
    }

    // Si vous utilisez l'Enum dans la base de données, vous pouvez ajouter des méthodes de validation
    public static Role fromEnum(RoleEnum roleEnum) {
        return new Role(roleEnum.name());
    }

    public enum RoleEnum {
        UTILISATEUR, 
        ADMIN,
        ARTISTE
    }
}
