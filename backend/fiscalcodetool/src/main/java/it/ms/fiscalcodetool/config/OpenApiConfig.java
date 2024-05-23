package it.ms.fiscalcodetool.config;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    static {
        SpringDocUtils.getConfig().addAnnotationsToIgnore(
            org.springframework.web.bind.annotation.RestController.class
        );
    }

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("API")
                .packagesToScan("it.ms.fiscalcodetool.controller")
                .build();
    }

    @Bean
    public Info apiInfo() {
        return new Info().title("API Documentation")
                .description("Documentation for API")
                .version("1.0");
    }
}

