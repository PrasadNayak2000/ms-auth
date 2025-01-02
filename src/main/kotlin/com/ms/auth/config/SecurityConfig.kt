package com.ms.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth ->
                auth.requestMatchers(
                    "/api/v1/auth/login",
                    "/api/v1/auth/new-token",
                    "/api/v1/auth/authorization-code",
                    "/api/v1/auth/access-token"
                ).permitAll()
                auth.anyRequest().authenticated()
            }
        http.csrf { it.disable() }
        return http.build()
    }
}