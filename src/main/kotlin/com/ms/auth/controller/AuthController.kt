package com.ms.auth.controller

import com.ms.auth.constant.ResultInfoConstant.SUCCESS
import com.ms.auth.constant.ResultInfoConstant.UNAUTHORIZED_ACCESS_IN_APPLICATIONS
import com.ms.auth.dto.request.UserAuthRequest
import com.ms.auth.dto.request.UserRegistrationRequest
import com.ms.auth.service.KeycloakService
import com.ms.auth.util.ResultUtil
import org.keycloak.representations.AccessTokenResponse
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/api/v1/auth")
@EnableConfigurationProperties
class AuthController(
    private val keycloakService: KeycloakService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/registration")
    fun register(@Validated @RequestBody request: UserRegistrationRequest): ResponseEntity<Any> {
        return ResultUtil.generateResponse(SUCCESS, null)
    }

    //Normal login
    @PostMapping("/login")
    fun login(@Validated @RequestBody request: UserAuthRequest): ResponseEntity<Any> {
        logger.info("Login User with keycloak : {}", request.getUsername())
        val accessTokenResponse: AccessTokenResponse = keycloakService.userLogin(request)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(UNAUTHORIZED_ACCESS_IN_APPLICATIONS)
        return ResultUtil.generateResponse(SUCCESS, accessTokenResponse)
    }

    //Sign with identity providers
    @GetMapping("/authorization-code")
    fun login(): RedirectView {
        logger.info("Sign with identity providers")
        return RedirectView(keycloakService.getAuthCodeUrl())
    }

    //Authorization code callback url
    @GetMapping("/access-token")
    fun login(@RequestParam("code") code: String): ResponseEntity<Any> {
        logger.info("Login User with keycloak code: $code")
        return ResultUtil.generateResponse(SUCCESS, keycloakService.getAccessToken(code))
    }

    @GetMapping("/new-token")
    fun generateNewAccessToken(@RequestParam refreshToken: String): ResponseEntity<Any> {
        logger.info("Generate new token using refresh token")
        return ResultUtil.generateResponse(SUCCESS, keycloakService.getNewAccessToken(refreshToken))
    }

    @PostMapping("/logout")
    fun logout(@RequestParam refreshToken: String): ResponseEntity<Any> {
        logger.info("Logout user")
        return keycloakService.userLogout(refreshToken)
    }

}
