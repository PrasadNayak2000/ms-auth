package com.ms.auth.exception.handler

import com.ms.auth.constant.ResultInfoConstant.SYSTEM_ERROR
import com.ms.auth.constant.ResultInfoConstant.UNAUTHORIZED_ACCESS_IN_APPLICATIONS
import com.ms.auth.util.ResultUtil
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.io.PrintWriter
import java.io.StringWriter


@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(value = [Throwable::class])
    @ResponseBody
    protected fun handleConflict(ex: Throwable): ResponseEntity<Any> {
        logger.error(getStackTraceAsString(ex))
        logger.error("GlobalExceptionHandling:{}", ex.message)
        ex.localizedMessage
        return ResultUtil.generateError(SYSTEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [FeignException::class])
    @ResponseBody
    protected fun handleFeign(ex: FeignException): ResponseEntity<Any> {
        logger.error(getStackTraceAsString(ex))
        logger.error("GlobalExceptionHandling :{}", ex.message)
        ex.localizedMessage
        return ResultUtil.generateError(UNAUTHORIZED_ACCESS_IN_APPLICATIONS, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(AccessDeniedException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    fun accessDeniedException(ex: AccessDeniedException): ResponseEntity<Any> {
        logger.error("Access denied")
        return ResultUtil.generateError(UNAUTHORIZED_ACCESS_IN_APPLICATIONS, HttpStatus.FORBIDDEN)
    }

    //@ExceptionHandler(AuthenticationException::class, BadCredentialsException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    fun authenticationException(ex: RuntimeException): ResponseEntity<Any> {
        logger.error("Un authenticated")
        return ResultUtil.generateError(UNAUTHORIZED_ACCESS_IN_APPLICATIONS, HttpStatus.UNAUTHORIZED)
    }

    private fun getStackTraceAsString(ex: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        ex.printStackTrace(pw)
        return sw.toString()
    }
}