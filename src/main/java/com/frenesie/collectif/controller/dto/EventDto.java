package com.frenesie.collectif.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.frenesie.collectif.model.Artist;
import com.frenesie.collectif.model.Billet;
import com.frenesie.collectif.model.Event.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String title;
    private String location;
    private String description;
    private List<String> imageUrls;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private List<Artist> artists;
    private List<Billet> billets;
}