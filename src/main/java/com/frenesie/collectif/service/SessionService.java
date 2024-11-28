package com.frenesie.collectif.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    private SessionRegistry sessionRegistry;

    // Récupérer toutes les sessions actives
    public List<SessionInformation> getActiveSessions() {
        return sessionRegistry.getAllSessions(
            SecurityContextHolder.getContext().getAuthentication().getPrincipal(), 
            false
        );
    }

    // Invalider une session
    public void invalidateSession(String sessionId) {
        SessionInformation sessionInformation = sessionRegistry.getSessionInformation(sessionId);
        if (sessionInformation != null) {
            sessionInformation.expireNow();
        }
    }
}
