package com.m2ibank.config.security;



import java.beans.Customizer;
import java.util.Arrays;
import java.util.Collections;

import com.m2ibank.config.jwt.JwtAuthenticationEntryPoint;
import com.m2ibank.config.jwt.JwtRequestFilter;
import com.m2ibank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


// Classe de configuration pour la sécurité de l'application.
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Injection des dépendances pour le service utilisateur et le point d'entrée d'authentification JWT.
    private final UserService userService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    // Constructeur pour initialiser les services injectés.
    public SecurityConfig(UserService userService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.userService = userService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    // Bean pour configurer la chaîne de filtres de sécurité.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Désactiver CSRF (Cross-Site Request Forgery) pour les services REST, car ils utilisent des tokens plutôt que des cookies pour l'authentification.
                .csrf(csrf -> csrf.disable())

                // Configurer la gestion de la session pour être sans état, ce qui est typique pour les API REST qui utilisent des tokens (comme JWT) pour l'authentification.
                // Cela signifie que le serveur ne maintiendra pas d'état de session entre les requêtes.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configurer l'autorisation des requêtes HTTP :
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/notices","/contact","/api/auth/register", "/api/auth/login").permitAll()
                        .requestMatchers("/account/**", "/loans/**", "/cards/**").hasRole("USER")
                        .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("*").authenticated())

                // Configurer le traitement des exceptions pour utiliser le point d'entrée d'authentification JWT personnalisé.
                // Cela est utilisé pour envoyer une réponse d'erreur appropriée si l'authentification échoue.
                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(jwtAuthenticationEntryPoint))

                // Ajouter un filtre personnalisé (JwtRequestFilter dans cet exemple) avant le filtre d'authentification par nom d'utilisateur et mot de passe.
                // Ce filtre vérifie la présence d'un JWT valide dans les requêtes et l'utilise pour l'authentification.
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

// Construire et retourner la chaîne de filtres de sécurité configurée.
        return http.build();

    }

    // Injection de la configuration d'authentification.
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    // Bean pour obtenir le gestionnaire d'authentification.
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        // Obtention du gestionnaire d'authentification à partir de la configuration.
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Bean pour le filtre d'authentification JWT.
    @Bean
    public JwtRequestFilter jwtAuthenticationFilter() {
        // Création d'un nouveau filtre avec le service utilisateur.
        return new JwtRequestFilter(userService);
    }

    // Bean pour le chiffrement des mots de passe.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Utilisation de BCrypt pour le chiffrement des mots de passe.
        return new BCryptPasswordEncoder();
    }




}
