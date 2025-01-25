package com.wheekapi.app.security

import com.wheekapi.app.domain.entities.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import java.util.*
import javax.crypto.SecretKey


object JwtUtil {
    private const val EXPIRATION_TIME: Long = 86400000
    private var secretString: String = System.getenv("SECRET_KEY") ?: ""
    private val SECRET_KEY: SecretKey = Keys.hmacShaKeyFor(secretString.toByteArray())

    fun generateToken(user: User): String {
        val now = Date()
        val expiryDate = Date(now.time + EXPIRATION_TIME)
        return Jwts.builder().claims().subject(user.email).issuedAt(Date(System.currentTimeMillis())).expiration(expiryDate).and()
            .signWith(SECRET_KEY).compact()
    }

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser().verifyWith(SECRET_KEY).build()
        return parser.parseSignedClaims(token).payload
    }

    fun extractEmail(token: String): String {
        return getAllClaims(token).subject
    }

    private fun isTokenExpired(token: String): Boolean =
        getAllClaims(token).expiration.before(Date(System.currentTimeMillis()))

    fun isTokenValid(token: String, user: User): Boolean {
        val email = extractEmail(token)

        if (email != user.email && !isTokenExpired(token)) {
            throw JwtException("Wheek | Invalid token!")
        }

        return true
    }

    fun verifyToken(token: String): String {
        return "Esta es la clave xdxddxed $secretString"
    }
}
