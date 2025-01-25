package com.wheekapi.app.domain.repositories

import com.wheekapi.app.domain.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, String> {
    fun findByEmail(email: String): Optional<User>
}