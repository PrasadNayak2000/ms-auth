package com.ms.auth.dto.request

class UserRegistrationRequest(
    private var name: String,
    private var username: String,
    private var password: String,
    private var roles: List<String>
) {
    fun getName() = this.name;

    fun getUsername() = this.username;

    fun getPassword() = this.password;

    fun getRoles() = this.roles

    fun setRoles(roles: List<String>) {
        this.roles = roles
    }
}