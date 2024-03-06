package com.x250.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthFilter authFilter;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(Customizer.withDefaults())
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authorizeExchange(exchange ->
                        exchange
                                .pathMatchers(
                                        "/eureka/**",
                                        "/api/v1/auth/register",
                                        "/api/v1/auth/authenticate"
//                                        "/api/user/"

                                )
                                .permitAll()
                                .pathMatchers(GET, "/api/plant/")

                                .permitAll()
                                .pathMatchers(DELETE, "/api/users_plant/user/**")
                                .permitAll()
                                .anyExchange()
                                .authenticated())
                .addFilterAt(authFilter, SecurityWebFiltersOrder.AUTHENTICATION)
//
                ;

        return serverHttpSecurity.build();
    }


//    @Bean
//    public ServerAccessDeniedHandler accessDeniedHandler() {
//        return ((exchange, denied) ->
//                Mono.defer(()-> Mono.just(exchange.getResponse().setComplete().then()))
//                        .then(Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN))));
//    }
//
//    @Bean
//    public ServerAuthenticationEntryPoint authEntryPoint() {
//        return ((exchange, event) ->
//                Mono.defer(()-> Mono.just(exchange.getResponse().setComplete().then()))
//                        .then(Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))));
//    }

}