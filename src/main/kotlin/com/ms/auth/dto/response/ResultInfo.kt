package com.ms.auth.dto.response

data class ResultInfo(
    private val codeId: String,
    private val code: String,
    private val message: String,
    private val status: String
) {
    fun getCodeId() = this.codeId
    fun getCode() = this.code
    fun getMessage() = this.message
    fun getStatus() = this.status
}