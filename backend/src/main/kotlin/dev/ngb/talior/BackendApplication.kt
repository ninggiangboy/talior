package dev.ngb.talior

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@RequestMapping("/")
class BackendApplication {
	@GetMapping
	fun index(): String {
		return "Hello World!"
	}
}

fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}

