package com.frenesie.collectif.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private Integer id;

    @NotBlank(message = "Le nom de l'événement est requis")
    private String title;
    
    @NotBlank(message = "Le Lieu de l'événement est requis")
    private String location;
    
    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;
    
    private LocalDateTime startTime;

    private LocalDateTime endTime;
    
    private Status status;

    public enum Status {
        PLANNED, RUNNING, ENDED, CANCELED
    }
    
    public Event(String title, String location, String description, 
    		LocalDateTime startTime, LocalDateTime endTime) {
    	super();
    	this.title = title;
    	this.location = location;
    	this.description = description;
    	this.startTime = startTime;
    	this.endTime = endTime;
    }
    
    private List<Artist> artists;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
        	return true;
        if (obj == null || getClass() != obj.getClass()) 
        	return false;
        Event event = (Event) obj;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
