package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(HelloController.class)
public class HelloControllerTests {

	@Autowired
	private WebTestClient client;

	@Test
	public void getHello() throws Exception {
		client.get()
			.uri("/?name=Test")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody(String.class).isEqualTo("Hello Test!");
	}
}