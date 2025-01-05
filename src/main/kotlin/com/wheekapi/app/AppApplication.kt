package com.wheekapi.app
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class AppApplication

fun main(args: Array<String>) {
	runApplication<AppApplication>(*args)
}
