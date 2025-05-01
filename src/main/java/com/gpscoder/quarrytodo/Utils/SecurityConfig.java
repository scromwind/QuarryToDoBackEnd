package com.gpscoder.quarrytodo.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Usando la sintaxis de lambda para la configuración
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Configuración CORS
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permitir todas las solicitudes (para pruebas)
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Permitir solicitudes desde el frontend local (reemplaza con el origen correcto)
        configuration.setAllowedOrigins(List.of(
            "http://127.0.0.1:5500",
            "https://quarrytodobackend-production.up.railway.app"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
