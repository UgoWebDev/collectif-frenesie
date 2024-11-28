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
    private String name; // Exemple : "USER", "ADMIN"

    public Role() {
    	
    }

    public Role(String name) {
        this.name = name;
    }
}
