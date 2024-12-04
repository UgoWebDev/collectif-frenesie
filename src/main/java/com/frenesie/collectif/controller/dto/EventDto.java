package com.frenesie.collectif.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.frenesie.collectif.model.Event;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventDto {
    private Long id;
    private String title;
    private String location;
    private String description;
    private List<String> imageUrls;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Event.Statut status;
}
