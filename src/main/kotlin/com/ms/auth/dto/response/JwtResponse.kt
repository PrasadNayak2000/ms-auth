package com.ms.auth.dto.response

import java.util.*


class JwtResponse(
    private val username: String,
    private val token: String,
    private val issuedAt: Date,
    private val expiry: Date
) {
    fun getUsername() = this.username;
    fun getToken() = this.token;
    fun getIssuedAt() = this.issuedAt;
    fun getExpiry() = this.expiry;
}