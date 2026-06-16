package com.luzejc.bee_raves_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * General application configuration.
 */
@Configuration
public class appConfig {

    /**
     * Registers BCryptPasswordEncoder as a Spring bean.
     * BCrypt is the standard algorithm for securely hashing passwords.
     * By registering it here, Spring can inject it into any class that needs it
     * without having to create it manually with 'new'.
     */

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
