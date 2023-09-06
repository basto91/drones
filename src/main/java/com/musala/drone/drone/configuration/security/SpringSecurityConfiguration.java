package com.musala.drone.drone.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/api/v1/authenticate", "/h2-console/**/**",
                                "/resources/**", "/static/**", "/assets/**", "/index.html",
                                "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                                "/**/*.gif", "/**/*.svg", "/**/favicon.ico",
                                "/v2/api-docs", "/sicom-api-docs", "/configuration/**",
                                "/swagger*/**", "/webjars/**")
                        .permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
