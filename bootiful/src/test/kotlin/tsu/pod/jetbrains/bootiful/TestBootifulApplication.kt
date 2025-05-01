package tsu.pod.jetbrains.bootiful

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<BootifulApplication>().with(TestcontainersConfiguration::class).run(*args)
}
