package com.kodbook.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("KodBook API")
                                .description("This is the API documentation for KodBook.")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("KodBook Support")
                                                .email("support@kodbook.com")
                                                .url("https://kodbook.com")
                                )
                )
                .servers(List.of(
                        new Server().url("http://localhost:8081").description("Dev Server"),
                        new Server().url("https://kodbook.com").description("Prod Server")
                ));
    }
}
