package com.wheekapi.app
import org.hibernate.cfg.Environment
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class AppApplication

@Configuration
class ApplicationConfig(private val env: org.springframework.core.env.Environment) {
	fun getSecretKey(): String {
		return env.getProperty("SECRET_KEY") ?: ""
	}

}

fun main(args: Array<String>) {
	runApplication<AppApplication>(*args)
}
