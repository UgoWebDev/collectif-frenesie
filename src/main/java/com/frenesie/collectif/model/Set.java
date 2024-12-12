package com.frenesie.collectif.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class Set {
	
	@NotBlank
    private Integer id;
	
	@NotBlank
    private String title;

    
    @NotBlank
    private Artist artist;
    
    private String urlSoundCloud;
    
    private LocalDateTime dateSet;
    
    private List<Genre> genres;
   
}