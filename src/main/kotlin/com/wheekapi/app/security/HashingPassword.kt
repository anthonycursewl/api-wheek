package com.wheekapi.app.security
import de.mkammerer.argon2.Argon2Factory
import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory.Argon2Types

object HashingPassword {
    private val argon2: Argon2 = Argon2Factory.create(Argon2Types.ARGON2id)

    fun hashPassword(password: String): String {
        val iterations = 3
        val memory = 65536
        val parallelism = 1
        return argon2.hash(iterations, memory, parallelism, password.toCharArray())
    }

    fun verifyPassword(password: String, hashedPassword: String): Boolean {
        return argon2.verify(hashedPassword, password.toCharArray())
    }

}