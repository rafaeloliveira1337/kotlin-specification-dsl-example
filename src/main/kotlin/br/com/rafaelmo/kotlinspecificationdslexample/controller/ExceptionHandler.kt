package br.com.rafaelmo.kotlinspecificationdslexample.controller

import br.com.rafaelmo.kotlinspecificationdslexample.exception.InvalidAgeRangeException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(InvalidAgeRangeException::class)
    fun handleInvalidAgeRangeException(
        ex: InvalidAgeRangeException,
        request: WebRequest
    ): ResponseEntity<Any> {
        return handleExceptionInternal(ex, ex.message, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }
}