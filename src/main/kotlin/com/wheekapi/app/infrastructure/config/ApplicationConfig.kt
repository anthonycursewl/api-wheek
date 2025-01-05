package com.wheekapi.app.infrastructure.config

// TODO: Implement all these tasks
// 1. Connect to database (POSTGRESQL)
// 2. Connect to redis (CACHING)

class ApplicationConfig {
    private val dataBaseConfig: String = "POSTGRESQL"

    fun getDataBaseConfig(type: String): String {
        if (type == "POSTGRESQL") {
            return "Connected to $type"
        }

        if (type == "CACHING") {
            return "Connected to $type"
        }

        return "Type not found"
    }
}


