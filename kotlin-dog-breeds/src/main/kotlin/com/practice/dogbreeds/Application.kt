package com.practice.dogbreeds

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@OpenAPIDefinition(
    info =
        Info(
            title = "Dog Breed API V1",
            version = "1.0",
            description = "Kotlin project for Backend Developers",
        ),
)
@EnableCaching
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
