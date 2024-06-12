package com.sol.serviceshuffle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${service-log.url}")
    private String serviceLogUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(serviceLogUrl)
                .filter((request, next) -> next.exchange(request)
                        .doOnError(error -> System.out.println("Error occurred: " + error.getMessage())))
                .build();


    }
}
