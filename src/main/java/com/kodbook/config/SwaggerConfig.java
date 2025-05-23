package com.kodbook.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("KodBook API")
                        .description("This is the API documentation for KodBook.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("KodBook Support")
                                .email("support@kodbook.com")
                                .url("https://kodbook.com")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")
                        )
                );
    }

    @Bean
    public GroupedOpenApi authV2Api() {
        return GroupedOpenApi.builder()
                .group("auth-v2")
                .pathsToMatch("/api/v2/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi userV1Api() {
        return GroupedOpenApi.builder()
                .group("user-v1")
                .pathsToMatch("/api/v1/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi userV2Api() {
        return GroupedOpenApi.builder()
                .group("user-v2")
                .pathsToMatch("/api/v2/users/**")
                .build();
    }

}
