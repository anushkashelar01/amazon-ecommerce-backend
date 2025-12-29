package com.shopease.config;

import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // 🔓 Auth APIs (UNCHANGED)
                        .requestMatchers("/api/auth/**").permitAll()

                        // 🔐 Admin APIs (UNCHANGED)
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // 🛒 Product APIs (NEW – ADDED ONLY)
                        .requestMatchers(HttpMethod.POST, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/products/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/categories").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/admin/categories/**").hasRole("ADMIN")

                        // 👤 User APIs (UNCHANGED)
                        .requestMatchers("/api/user/**").hasRole("USER")

                        // ❌ Default rule (UNCHANGED)
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // BASIC AUTH

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}