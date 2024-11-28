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
                .sessionRegistry(sessionRegistry)
            )
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/home", "/register", "/login").permitAll() // Permet l'accès libre à /home, /register et /login
                .requestMatchers("/admin/**").hasRole("ADMIN") // Pages admin nécessitent un rôle ADMIN
                .requestMatchers("/user/**").hasRole("USER")   // Pages utilisateur nécessitent un rôle USER
                .anyRequest().authenticated() // Toute autre page requiert une authentification
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)  // Rediriger vers /home après la connexion
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/home")  // Rediriger vers /home après la déconnexion
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
