package com.kodbook.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${services.user-service.client.username}")
    private String userServiceClientUsername;
    @Value("${services.user-service.client.password}")
    private String userServiceClientPassword;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .basicAuthentication(userServiceClientUsername, userServiceClientPassword)
                .build();
    }
}
