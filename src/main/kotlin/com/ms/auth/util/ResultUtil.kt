package com.ms.auth.util

import com.ms.auth.dto.response.GenericResponse
import com.ms.auth.dto.response.ResultInfo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


object ResultUtil {

    fun generateResponse(resultInfo: ResultInfo, resObj: Any?): ResponseEntity<Any> {
        return ResponseEntity<Any>(
            GenericResponse(resultInfo, resObj), HttpStatus.OK
        )
    }

    fun generateError(resultInfo: ResultInfo, httpStatus: HttpStatus): ResponseEntity<Any> {
        return ResponseEntity<Any>(
            GenericResponse(resultInfo, null), httpStatus
        )
    }
}