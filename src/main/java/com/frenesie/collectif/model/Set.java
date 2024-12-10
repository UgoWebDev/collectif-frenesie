package com.frenesie.collectif.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sets")
@Valid
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String urlSoundCloud;

    @Column(nullable = false)
    private LocalDateTime dateSet;

    @ManyToOne
    @JoinColumn(name = "collectif_id", nullable = false)
    private Collectif collectif;
    
    @ManyToOne
    @JoinColumn(name = "artiste_id")
    private Artist artist;
   
}