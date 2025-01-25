package com.wheekapi.app.infrastructure.adapters.input

import io.jsonwebtoken.JwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

// Annotations
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import jakarta.persistence.EntityNotFoundException
import org.springframework.web.client.HttpClientErrorException.Unauthorized

@ControllerAdvice
@RestController
class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("${e.message}")
    }

    @ExceptionHandler(Unauthorized::class)
    fun handleExceptionBadRequest(e: Unauthorized): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("${e.message}")
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("${e.message}")
    }

    @ExceptionHandler(JwtException::class)
    fun handleJwtException(e: JwtException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wheek | ${e.message}")
    }
}