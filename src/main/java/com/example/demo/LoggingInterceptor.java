package com.example.demo;

import java.util.Optional;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingInterceptor implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest();

        final String method = req.getMethod().toString();
        final String path = req.getPath().toString();
        final String address = Optional.ofNullable(req.getRemoteAddress()).map(addr -> addr.toString()).orElseGet(() -> "localhost");

        log.info("Request {} {} from IP {}", method, path, address);

        return chain.filter(exchange);
    }
}
