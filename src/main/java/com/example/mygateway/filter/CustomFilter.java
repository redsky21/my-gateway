package com.example.mygateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public static class Config{
        // Put the configuration propertis

    }
    public CustomFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Custom pre filter

        return ((exchange, chain) -> {
            ServerHttpRequest request =  exchange.getRequest();
            log.info("Custom pre filter::: request id->{}",request.getId());
            ServerHttpResponse response = exchange.getResponse();
            //post fitler
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("Custom pre filter::: response code->{}",response.getStatusCode());
            }));
        });
    }
}
