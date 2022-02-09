package com.example.demo;

import org.slf4j.Logger;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class LoggingInterceptor implements WebFilter {

    private final Logger logger;

    public LoggingInterceptor(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest();

        final String method = req.getMethod().toString();
        final String path = req.getPath().toString();
        final String address = req.getRemoteAddress().toString();

        logger.info("Request {} {} from IP {}", method, path, address);

        return chain.filter(exchange);
    }
}
