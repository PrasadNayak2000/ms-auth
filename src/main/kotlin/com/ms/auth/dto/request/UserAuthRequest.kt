package com.ms.auth.dto.request

class UserAuthRequest(private val username: String, private val password: String) {
    fun getUsername() = this.username;

    fun getPassword() = this.password;
}