package com.niit.APIGatewayService.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGateWayConfig {

    @Bean
    public RouteLocator getRoutes(RouteLocatorBuilder builder) {

        return builder.routes().route(p -> p.path("/api/v2/**").
                        uri("lb://Track-service")).
                route(p -> p.path("/api/v2/**").
                        uri("lb://User-service")).build();
    }

}
