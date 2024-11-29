package com.frenesie.collectif.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sets")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    private String urlSoundCloud;

    @Column(nullable = false)
    private LocalDateTime datePerformance;

    @ManyToOne
    @JoinColumn(name = "collectif_id", nullable = false)
    private Collectif collectif;
    
    @ManyToOne
    @JoinColumn(name = "artiste_id")
    private Artiste artiste;
}