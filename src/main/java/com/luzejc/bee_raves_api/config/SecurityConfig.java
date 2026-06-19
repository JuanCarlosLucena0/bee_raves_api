package com.luzejc.bee_raves_api.config;

import com.luzejc.bee_raves_api.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Application security configuration.
 * Defines which routes are public, which require authentication,
 * and how each HTTP request is processed before reaching the controller.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // JWT filter that intercepts every request to verify the token
    private final JwtFilter jwtFilter;

    /**
     * Defines the security filter chain that processes each HTTP request.
     * This is the central point where all security rules are configured.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                // Disable CSRF protection as it does not apply to REST APIs using JSON
                .csrf(scrf -> scrf.disable())

                // No sessions: each request authenticates independently with its token
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public routes: no token required
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/scores").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/scores/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/health").permitAll()

                        // Any other route requires a valid token
                        .anyRequest().authenticated())

                // Adds the JWT filter before Spring's default authentication filter
                // so every request goes through token verification first
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

}
