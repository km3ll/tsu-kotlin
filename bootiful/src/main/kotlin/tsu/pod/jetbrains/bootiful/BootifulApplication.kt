package tsu.pod.jetbrains.bootiful

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter
import tsu.pod.jetbrains.bootiful.model.CustomerRepository

@SpringBootApplication
class BootifulApplication {

	@Bean
	fun http(customerRepository: CustomerRepository) = coRouter {

		GET("/customers") {
			ServerResponse.ok().bodyAndAwait(customerRepository.findAll())
		}

		GET("/customers/{id}") {
			val id = it.pathVariable("id").toInt()
			val result = customerRepository.findById(id)
			if (result != null)
				ServerResponse.ok().bodyValueAndAwait(result)
			else
				ServerResponse.notFound().buildAndAwait()
		}

	}

}

fun main(args: Array<String>) {
	runApplication<BootifulApplication>(*args)
}