package com.ms.auth.service.impl

import ValidationException
import com.ms.auth.constant.ResultInfoConstant.SOMETHING_WENT_WRONG
import com.ms.auth.constant.ResultInfoConstant.SUCCESS
import com.ms.auth.dto.request.UserAuthRequest
import com.ms.auth.service.KeycloakService
import com.ms.auth.service.feign.KeycloakClient
import com.ms.auth.util.ResultUtil
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.AccessTokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class KeycloakServiceImpl(private val keycloakFeignClient: KeycloakClient) : KeycloakService {

    override fun userLogin(request: UserAuthRequest): AccessTokenResponse? {
        val loginKeycloak = buildKeycloak(request.getUsername(), request.getPassword())

        val accessTokenResponse: AccessTokenResponse?
        try {
            accessTokenResponse = loginKeycloak.tokenManager().accessToken
            return accessTokenResponse
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private fun buildKeycloak(username: String, password: String): Keycloak {
        return KeycloakBuilder.builder()
            .realm("spring-keycloak")
            .serverUrl("http://localhost:8080/")
            .clientId("spring-service")
            .clientSecret("baUZUl5KVQHHe5r79LQBowYK7d8TuYpF")
            .username(username)
            .password(password)
            .build()
    }

    override fun getNewAccessToken(refreshToken: String): Map<String, String?>? {
        val body = HashMap<String, String>()
        body["grant_type"] = "refresh_token"
        body["client_id"] = "spring-service"
        body["client_secret"] = "baUZUl5KVQHHe5r79LQBowYK7d8TuYpF"
        body["refresh_token"] = refreshToken
        val response: Map<String, String?>? =
            keycloakFeignClient.getAccessToken(endpointUrl = "token", body = body).body
        return response
    }

    override fun userLogout(refreshToken: String): ResponseEntity<Any> {
        val body = HashMap<String, String>()
        //body["grant_type"] = "refresh_token"
        body["redirect_uri"] = "http://localhost:7000/auth/api/v1/auth/logout"
        body["client_id"] = "spring-service"
        body["client_secret"] = "baUZUl5KVQHHe5r79LQBowYK7d8TuYpF"
        body["refresh_token"] = refreshToken
        try {
            keycloakFeignClient.logout("spring-service", refreshToken, body)
            return ResultUtil.generateResponse(SUCCESS, null)
        } catch (e: Exception) {
            e.message
            throw ValidationException(SOMETHING_WENT_WRONG)
        }
    }
}