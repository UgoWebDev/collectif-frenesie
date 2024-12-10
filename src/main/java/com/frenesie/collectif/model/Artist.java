package com.frenesie.collectif.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "artists")
@Valid
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Le name est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom de l'artiste doit avoir entre 2 et 100 caractères")
    private String name;

    @Column(length = 500)
    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;

    @Column(nullable = false)
    private String genre;
    
    @ElementCollection
    @CollectionTable(
        name = "artist_image_urls",
        joinColumns = @JoinColumn(name = "artist_id")
    )
    @Column(name = "image_url")
    private List<String> imageUrls;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Set> sets = new ArrayList<>();

    @ManyToMany(mappedBy = "artists")
    private List<Event> events = new ArrayList<>();

    public Artist(String name, String description, String genre) {
        this.name = name;
        this.description = description;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Artist artist = (Artist) obj;
        return Objects.equals(id, artist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
