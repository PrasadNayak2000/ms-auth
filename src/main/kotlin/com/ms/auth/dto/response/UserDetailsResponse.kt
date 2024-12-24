package com.ms.auth.dto.response

class UserDetailsResponse {
    private var id: Int? = null
    private var name: String? = null
    private var username: String? = null

    fun getId() = this.id

    fun getName() = this.name

    fun getUsername() = this.username

    fun setId(id: Int?) {
        this.id = id
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun setUsername(username: String?) {
        this.username = username
    }
}