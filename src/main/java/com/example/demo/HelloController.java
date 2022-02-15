package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {

	@GetMapping("/")
	public String index(@RequestParam(value = "name", defaultValue = "World") String name) {
		log.info("I said hello to {}", name);
		return String.format("Hello %s!", name);
	}
}
