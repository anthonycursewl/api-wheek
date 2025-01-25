package com.wheekapi.app.infrastructure.adapters.input

import com.wheekapi.app.application.useCases.AuthUseCase
import com.wheekapi.app.application.useCases.UserUseCase
import com.wheekapi.app.domain.entities.User
import com.wheekapi.app.security.HashingPassword
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val userUseCase: UserUseCase, private val authUseCase: AuthUseCase) {

    data class ResponseAuth(val message: String, val token: String)

    @PostMapping("/login")
    fun authorization(@RequestBody user: User): ResponseEntity<ResponseAuth> {
        val foundUser = userUseCase.getUserByEmail(user.email)
        val isValidPassword = HashingPassword.verifyPassword(user.password, foundUser.password)
        if (!isValidPassword) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseAuth("Wheek | Unauthorized", ""))
        }

        val token = authUseCase.authenticate(user)
        return ResponseEntity.ok(ResponseAuth("Welcome back ${foundUser.username}!", token))
    }

    @GetMapping("/verify")
    fun validateToken(@RequestHeader("Authorization") token: String): ResponseEntity<User> {
        val user = authUseCase.validateToken(token)
        return ResponseEntity.ok(user)
    }

}
