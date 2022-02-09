package com.example.demo;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController {

	private final Logger logger;

	public HelloController(Logger logger) {
		this.logger = logger;
	}

	@GetMapping("/")
	public String index(@RequestParam(value = "name", defaultValue = "World") String name) {
		logger.info("I said hello to {}", name);

		return String.format("Hello %s!", name);
	}
}
