package com.example.mygateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    @Data
    public static class Config{
        // Put the configuration propertis
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;

    }
    public GlobalFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {


        return ((exchange, chain) -> {
            //Custom pre filter
            ServerHttpRequest request =  exchange.getRequest();
//            log.info("Global pre filter::: request id->{}",request.getId());
            log.info("Global filter message ::: {}",config.getBaseMessage());
            ServerHttpResponse response = exchange.getResponse();
            if(config.isPreLogger()){
                log.info("Global pre filter start ::: request id->{}",request.getId());
            }



            //post fitler
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if(config.isPostLogger()){
                    log.info("Global post filter End::: response code->{}",response.getStatusCode());
                }
//                log.info("Global post filter::: response code->{}",response.getStatusCode());
            }));
        });
    }
}
