package com.ms.auth.service.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "keycloak-client", url = "http://localhost:8080/realms/spring-keycloak/protocol/openid-connect")
interface KeycloakClient {

    @PostMapping(value = ["/token"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun getAccessToken(body: Map<String, String>): ResponseEntity<Map<String, String?>>

    @GetMapping(value = ["/logout"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun logout(
        @RequestParam("client_id") clientId: String,
        @RequestParam("refresh_token") refreshToken: String,
        body: Map<String, String>
    )

}