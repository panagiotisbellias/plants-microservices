package com.x250.plants.api.gateway.exception_handler;

import com.google.gson.Gson;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SignatureException.class)
    public Mono<Void> handleSignatureException(ServerWebExchange exchange, SignatureException ex) {
        return handleServerAuthenticationEntryPoint(exchange, ex);
    }

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException authException) {
        return handleServerAuthenticationEntryPoint(exchange, authException);
    }

    private Mono<Void> handleServerAuthenticationEntryPoint(ServerWebExchange exchange, Exception exception) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        byte[] errorResponse = new Gson().toJson(errorMap).getBytes(StandardCharsets.UTF_8);

        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(errorResponse);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}
