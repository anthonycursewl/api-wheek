package com.wheekapi.app.infrastructure.adapters.input

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// TODO: Create controller
import com.wheekapi.app.application.useCases.UserUseCase
import com.wheekapi.app.domain.entities.User
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@RestController
@RequestMapping("/users")
class UserController(private val userUseCase: UserUseCase) {

    @PostMapping("/create")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userUseCase.createUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
    }

    @GetMapping("/list")
    fun getAllUsers(
        @RequestParam("page", defaultValue = "1", required = false) page: Int,
        @RequestParam("count", defaultValue = "10", required = false) count: Int
        ): ResponseEntity<List<User>> {

        val users = userUseCase.getAllUsers()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/get")
    fun getUserById(@RequestParam("id") id: String): ResponseEntity<User> {
        val user = userUseCase.getUserById(id)
        return ResponseEntity.ok(user)
    }

}