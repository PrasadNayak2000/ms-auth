package com.ms.auth.constant

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class KeycloakConstant {

    @Value("\${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    lateinit var issuerUrl: String

    @Value("\${spring.security.oauth2.client.provider.keycloak.authorization-uri}")
    lateinit var authorizationUrl: String

    @Value("\${spring.security.oauth2.client.registration.keycloak.name}")
    lateinit var realm: String

    @Value("\${spring.security.oauth2.client.registration.keycloak.client-id}")
    lateinit var clientId: String

    @Value("\${spring.security.oauth2.client.registration.keycloak.client-secret}")
    lateinit var clientSecret: String

    @Value("\${auth.authorization-code-redirect-uri}")
    lateinit var authCodeRedirectionUrl: String

    @Value("\${auth.logout-redirect-uri}")
    lateinit var logoutRedirectionUrl: String


    val grantTypeKey = "grant_type"
    val clientIdKey = "client_id"
    val clientSecretKey = "client_secret"
    val refreshTokenKey = "refresh_token"
    val authorizationCodeKey = "code"
    val redirectURIKey = "redirect_uri"

    val refreshTokenGrant = "refresh_token"
    val authorizationCodeGrant = "authorization_code"

}