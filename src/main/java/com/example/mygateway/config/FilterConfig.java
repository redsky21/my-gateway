package com.example.mygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r.path("/first-service/**")
                .filters(f -> f.addRequestHeader("first-request", "firstReqHeader1")
                    .addResponseHeader("firstResponseHeader", "firstRespHeader1"))
                .uri("http://127.0.0.1:8081"))
            .route(r -> r.path("/second-service/**")
                .filters(f -> f.addRequestHeader("second-request", "secondtReqHeader1")
                    .addResponseHeader("firstResponseHeader", "firstRespHeader1"))
                .uri("http://127.0.0.1:8082"))

            .build();
    }
}
