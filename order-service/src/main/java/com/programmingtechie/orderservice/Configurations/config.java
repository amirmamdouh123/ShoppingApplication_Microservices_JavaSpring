package com.programmingtechie.orderservice.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class config {

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
