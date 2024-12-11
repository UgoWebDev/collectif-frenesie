package com.frenesie.collectif.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
    private final AuthenticationSuccessHandler authenticationSuccessHandler = new AuthenticationSuccessHandler() {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, 
                                            HttpServletResponse response, 
                                            Authentication authentication) throws IOException {
            boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            
            if (isAdmin) {
                response.sendRedirect("/admin/dashboard");
            } else {
                response.sendRedirect("/home");
            }
        }
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(requests -> requests
                // Pages publiques accessibles à tous
                .requestMatchers("/", "/home", "/events", "/sets", "/artists", "/ticketing").permitAll()
                // Pages d'authentification
                .requestMatchers("/login", "/register").permitAll()
                // Ressources statiques
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                // Pages de réservation de billets nécessitent une authentification
                .requestMatchers("/tickets/book/**").authenticated()
                // Pages des artistes : accessible uniquement à l'artiste connecté et à l'admin
                .requestMatchers("/artist/profile/**").hasAnyRole("ARTIST", "ADMIN")
                // Routes d'administration
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Toute autre route nécessite une authentification
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .successHandler(authenticationSuccessHandler) 
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")  // Added logout parameter
                .permitAll());
            
            return http.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    
    @Bean
    SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    AuthenticationManager authenticationManager(
        AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }
}