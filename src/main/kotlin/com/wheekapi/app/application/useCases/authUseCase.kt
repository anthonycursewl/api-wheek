package com.wheekapi.app.application.useCases

import com.wheekapi.app.domain.entities.User
import com.wheekapi.app.security.HashingPassword
import com.wheekapi.app.security.JwtUtil
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service

@Service
class AuthUseCase(private val userUseCase: UserUseCase) {

    fun authenticate(user: User): String {
        val foundUser = userUseCase.getUserByEmail(user.email)
        val isValidPassword = HashingPassword.verifyPassword(user.password, foundUser.password)
        if (!isValidPassword) {
            throw Exception("Wheek | Unauthorized")
        }
        return JwtUtil.generateToken(user)
    }

    fun validateToken(token: String): User? {
        val email = JwtUtil.extractEmail(token)
        val foundUser = userUseCase.getUserByEmail(email)

        val isValidToken = JwtUtil.isTokenValid(token, foundUser)
        return if (isValidToken) foundUser else null
    }

}