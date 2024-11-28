package com.frenesie.collectif.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/active")
    public ResponseEntity<?> getActiveSessions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<SessionInformation> sessions = sessionRegistry.getAllSessions(authentication.getPrincipal(), false);
        
        return ResponseEntity.ok(sessions.stream()
            .map(session -> {
                Map<String, Object> sessionInfo = new HashMap<>();
                sessionInfo.put("sessionId", session.getSessionId());
                sessionInfo.put("lastRequest", session.getLastRequest());
                return sessionInfo;
            })
            .collect(Collectors.toList())
        );
    }

    @PostMapping("/invalidate/{sessionId}")
    public ResponseEntity<?> invalidateSession(@PathVariable String sessionId) {
        SessionInformation sessionInformation = sessionRegistry.getSessionInformation(sessionId);
        if (sessionInformation != null) {
            sessionInformation.expireNow();
            return ResponseEntity.ok("Session invalidated");
        }
        return ResponseEntity.notFound().build();
    }
}
