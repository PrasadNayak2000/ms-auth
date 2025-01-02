package com.ms.auth.service.impl

import ValidationException
import com.ms.auth.constant.KeycloakConstant
import com.ms.auth.constant.ResultInfoConstant.SOMETHING_WENT_WRONG
import com.ms.auth.constant.ResultInfoConstant.SUCCESS
import com.ms.auth.dto.request.UserAuthRequest
import com.ms.auth.service.KeycloakService
import com.ms.auth.service.feign.KeycloakClient
import com.ms.auth.util.ResultUtil
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.AccessTokenResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class KeycloakServiceImpl(private val keycloakFeignClient: KeycloakClient) : KeycloakService {

    @Autowired
    private lateinit var keycloakConstant: KeycloakConstant

    override fun userLogin(request: UserAuthRequest): AccessTokenResponse? {
        val loginKeycloak = KeycloakBuilder.builder()
            .realm(keycloakConstant.realm)
            .serverUrl("http://localhost:8080/")
            .clientId(keycloakConstant.clientId)
            .clientSecret(keycloakConstant.clientSecret)
            .username(request.getUsername())
            .password(request.getPassword())
            .build()

        val accessTokenResponse: AccessTokenResponse?
        try {
            accessTokenResponse = loginKeycloak.tokenManager().accessToken
            return accessTokenResponse
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override fun getNewAccessToken(refreshToken: String): Map<String, String?>? {
        val body = HashMap<String, String>()
        body[keycloakConstant.grantTypeKey] = keycloakConstant.refreshTokenGrant
        body[keycloakConstant.clientIdKey] = keycloakConstant.clientId
        body[keycloakConstant.clientSecretKey] = keycloakConstant.clientSecret
        body[keycloakConstant.refreshTokenKey] = refreshToken
        val response: Map<String, String?>? =
            keycloakFeignClient.getAccessToken(body).body
        return response
    }

    override fun getAccessToken(code: String): Map<String, String?>? {
        val body = HashMap<String, String>()
        body[keycloakConstant.grantTypeKey] = keycloakConstant.authorizationCodeGrant
        body[keycloakConstant.clientIdKey] = keycloakConstant.clientId
        body[keycloakConstant.clientSecretKey] = keycloakConstant.clientSecret
        body[keycloakConstant.authorizationCodeKey] = code
        body[keycloakConstant.redirectURIKey] = keycloakConstant.authCodeRedirectionUrl
        val response: Map<String, String?>? =
            keycloakFeignClient.getAccessToken(body).body
        return response
    }

    override fun getAuthCodeUrl(): String {
        return keycloakConstant.authorizationUrl +
                "?response_type=code&client_id=" + keycloakConstant.clientId +
                "&redirect_uri=" + keycloakConstant.authCodeRedirectionUrl + "&scope=openid"
    }

    override fun userLogout(refreshToken: String): ResponseEntity<Any> {
        val body = HashMap<String, String>()
        body[keycloakConstant.redirectURIKey] = keycloakConstant.logoutRedirectionUrl
        body[keycloakConstant.clientIdKey] = keycloakConstant.clientId
        body[keycloakConstant.clientSecretKey] = keycloakConstant.clientSecret
        body[keycloakConstant.refreshTokenKey] = refreshToken
        try {
            keycloakFeignClient.logout(keycloakConstant.clientId, refreshToken, body)
            return ResultUtil.generateResponse(SUCCESS, null)
        } catch (e: Exception) {
            e.message
            throw ValidationException(SOMETHING_WENT_WRONG)
        }
    }
}