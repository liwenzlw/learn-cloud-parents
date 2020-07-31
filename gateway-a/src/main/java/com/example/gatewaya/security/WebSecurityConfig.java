package com.example.gatewaya.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
       http.csrf().disable()
               .authorizeExchange().anyExchange()
               .authenticated()
               .pathMatchers("/actuator/**")
               .permitAll()
               .and()
               .formLogin()
               .and()
               .httpBasic();
       return http.build();
    }
}