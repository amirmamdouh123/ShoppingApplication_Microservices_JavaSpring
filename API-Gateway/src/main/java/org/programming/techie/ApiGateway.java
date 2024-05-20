package org.programming.techie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateway
{
    public static void main( String[] args )
    {
        SpringApplication.run(ApiGateway.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r.path("/api/product")
                        .uri("lb://product-service"))
                .route("order-service", r -> r.path("/api/order")
                        .uri("lb://order-service"))
                .route("inventory-service", r -> r.path("/api/inventory")
                        .uri("lb://inventory-service"))
                .build();
    }
}
