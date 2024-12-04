package com.frenesie.collectif.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByStartTimeBeforeOrderByStartTimeDesc(LocalDateTime now, Pageable pageable);
    Page<Event> findByStartTimeAfterOrderByStartTimeAsc(LocalDateTime now, Pageable pageable);
}