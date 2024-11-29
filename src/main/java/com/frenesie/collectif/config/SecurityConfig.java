package com.frenesie.collectif.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SessionRegistry sessionRegistry) throws Exception {
        http
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry) // Use the injected sessionRegistry
            )
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/home", "/login", "/register").permitAll()  // Accès libre à /home
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Accès aux pages admin avec rôle ADMIN
                .requestMatchers("/user/**").hasRole("USER")   // Accès aux pages utilisateur avec rôle USER
                .anyRequest().authenticated()  // Toutes les autres pages nécessitent une authentification
            )
            .formLogin(form -> form
                .loginPage("/login")  // Page de connexion personnalisée
                .defaultSuccessUrl("/home", true)  // Redirige vers /home après une connexion réussie
                .permitAll()  // Permet à tout le monde d'accéder à la page de connexion
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/home")  // Redirige vers /home après la déconnexion
                .permitAll()  // Permet à tout le monde d'accéder à la déconnexion
            );

        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl(); // Create and return SessionRegistry
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
