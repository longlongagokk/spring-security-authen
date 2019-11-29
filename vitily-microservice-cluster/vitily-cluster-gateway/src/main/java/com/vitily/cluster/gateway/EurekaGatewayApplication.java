package com.vitily.cluster.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Slf4j
public class EurekaGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaGatewayApplication.class, args);
    }
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("auth",r->r.path("/auth/**","/mem/**").uri("lb://member-center"))
                .build();
    }
}
