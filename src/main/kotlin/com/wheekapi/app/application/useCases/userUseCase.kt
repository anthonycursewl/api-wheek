package com.wheekapi.app.application.useCases
import com.wheekapi.app.domain.entities.User
import com.wheekapi.app.domain.repositories.UserRepository
import com.wheekapi.app.security.HashingPassword
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

// Security

@Service
class UserUseCase(private val userRepository: UserRepository) {

    fun getAllUsers(): List<User> {
        return userRepository.findAll() ?: emptyList()
    }

    fun createUser(user: User): User {
        if (user.password.length > 100) throw Exception("Wheek | Password too long")
        if (user.username.length > 150) throw Exception("Wheek | Username too long")

        val hashedPassword = HashingPassword.hashPassword(user.password)
        val newUser = user.copy(password = hashedPassword)
        return userRepository.save(newUser)
    }

    fun getUserById(id: String): User {
        val user = userRepository.findById(id).getOrNull() ?: throw EntityNotFoundException("Wheek | User not found")
        return user
    }

    fun getUserByEmail(email: String): User {
        val user = userRepository.findByEmail(email).getOrNull() ?: throw EntityNotFoundException("Wheek | User not found")
        return user
    }
}
