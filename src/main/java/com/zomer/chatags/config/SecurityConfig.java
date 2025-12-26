package com.zomer.chatags.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/* Configura la seguridad proporcionada automaticamente por la libreria security */
/* Deshabilita esa opcion en desarrollo */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Desactivar CSRF, para probar con endpoints.
                .csrf(csrf -> csrf.disable())
                // Rutas Autorizadas .permitAll() Todas.
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/test").permitAll();
                    // auth.requestMatchers("/test", "/auth/**", "/public/**").permitAll();
                    // auth.anyRequest().authenticated();
                })
                .cors(Customizer.withDefaults())
                .build();
    }
}
