package com.frenesie.collectif.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArtistDto {
    private Integer id;
    private String nom;
    private String biography;
    private String genre;
}
