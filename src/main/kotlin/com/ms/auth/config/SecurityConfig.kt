package com.ms.auth.config

import com.ms.auth.constant.KeycloakConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Autowired
    private lateinit var keycloakConstant: KeycloakConstant

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth ->
                auth.requestMatchers(
                    *openEndpoints.toTypedArray()
                ).permitAll()
                auth.anyRequest().authenticated()
            }
            .oauth2ResourceServer { oauth2 ->
                oauth2.jwt { t -> t.jwkSetUri(keycloakConstant.tokenValidationUrl) }
            }
        http.csrf { it.disable() }
        return http.build()
    }

    private var openEndpoints = listOf(
        "/api/v1/auth/login",
        "/api/v1/auth/new-token",
        "/api/v1/auth/authorization-code",
        "/api/v1/auth/access-token"
    )
}