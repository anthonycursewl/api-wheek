package com.wheekapi.app.infrastructure.adapters.input
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
class HelloControler {

    @GetMapping("/login")
    fun littleHello(): String {
        return "Login users uwu"
    }

    @GetMapping("/register")
    fun registerUser(): String {
        return "Register user uwu"
    }
}