package com.wheekapi.app.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.sql.Date

@Entity
@Table(name = "users")
data class User (
    @Id
    @Column(name = "id", nullable = false)
    val id: String,

    @Column(name = "username", nullable = false)
    val username: String,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: Date,

    @Column(name = "role", nullable = false)
    val role: String,

    @Column(name = "verified", nullable = false)
    val verified: Boolean
) {
    constructor() : this("", "", "", "", Date(0), "", false)
}