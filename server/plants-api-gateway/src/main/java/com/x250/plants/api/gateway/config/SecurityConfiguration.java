package com.x250.plants.api.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

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
                                .pathMatchers(HttpMethod.OPTIONS)
                                .permitAll()
                                .pathMatchers(
                                        "/eureka/**",
                                        "/api/v1/auth/**",
                                        "/stomp/**",

                                        "/auth/**",
                                        "/oauth2/**"

                                )
                                .permitAll()
//                                .pathMatchers(GET, "/api/plant/")
//                                .permitAll()
//                                .pathMatchers(DELETE, "/api/users_plant/user/**")
//                                .permitAll()
                                .anyExchange()
                                .authenticated())
                .addFilterAt(authFilter, SecurityWebFiltersOrder.AUTHENTICATION)
//
        ;

        return serverHttpSecurity.build();
    }

}