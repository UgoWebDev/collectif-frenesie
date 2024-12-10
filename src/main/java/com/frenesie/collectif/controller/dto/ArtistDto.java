package com.frenesie.collectif.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.frenesie.collectif.model.Event;
import com.frenesie.collectif.model.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArtistDto {
    private Integer id;
    private String nom;
    private String description;
    private String genre;
    private List<String> imageUrls;
    private List<Set> sets = new ArrayList<>();
    private List<Event> events = new ArrayList<>();

}
