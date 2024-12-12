package com.frenesie.collectif.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    private Integer id;	

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom de l'artiste doit avoir entre 2 et 100 caractères")
    private String name;

    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String biography;
    
    private String genre;
    
    public Artist(String name, String biography, String genre) {
    	super();
    	this.name = name;
    	this.biography = biography;
    	this.genre = genre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
        	return true;
        if (obj == null || getClass() != obj.getClass()) 
        	return false;
        Artist artist = (Artist) obj;
        return Objects.equals(id, artist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
