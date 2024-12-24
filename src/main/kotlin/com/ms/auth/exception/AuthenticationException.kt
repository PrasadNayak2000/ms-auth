package com.ms.auth.exception

import com.ms.auth.dto.response.ResultInfo

class AuthenticationException(private val resultInfo: ResultInfo) : RuntimeException() {
    fun getResultInfo() = this.resultInfo
}