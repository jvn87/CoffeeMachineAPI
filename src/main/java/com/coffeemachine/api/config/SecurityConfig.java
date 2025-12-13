package com.coffeemachine.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos (qualquer utilizador, mesmo não autenticado)
                        .requestMatchers(new AntPathRequestMatcher("/coffees/**")).permitAll()

                        // Endpoints reservados a utilizadores registados (role USER)
                        .requestMatchers(new AntPathRequestMatcher("/orders/**")).hasAnyRole("USER","ADMIN")

                        // Endpoints reservados a administradores (role ADMIN)
                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")

                        // Qualquer outro endpoint exige autenticação
                        .anyRequest().authenticated()
                )
                .httpBasic(); // autenticação básica para Postman
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}