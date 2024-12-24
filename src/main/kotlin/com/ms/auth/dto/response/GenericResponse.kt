package com.ms.auth.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class GenericResponse(private val resultInfo: ResultInfo, private val data: Any?) {
    fun getResultInfo() = this.resultInfo
    fun getData() = this.data
}