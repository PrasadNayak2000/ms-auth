package com.ms.auth.service

import com.ms.auth.dto.request.UserAuthRequest
import org.keycloak.representations.AccessTokenResponse
import org.springframework.http.ResponseEntity

interface KeycloakService {

    fun userLogin(request: UserAuthRequest): AccessTokenResponse?

    fun getNewAccessToken(refreshToken: String): Map<String, String?>?

    fun userLogout(refreshToken: String): ResponseEntity<Any>

    fun getAccessToken(code: String): Map<String, String?>?

    fun getAuthCodeUrl(): String
}
