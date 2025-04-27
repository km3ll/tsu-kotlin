package tsu.pod.jetbrains.bootiful

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BootifulApplication

fun main(args: Array<String>) {
	runApplication<BootifulApplication>(*args)
}
