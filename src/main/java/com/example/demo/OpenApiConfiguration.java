package com.example.demo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI demoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Demo Sum API")
                        .version("v1")
                        .description("REST API for adding two whole numbers.")
                        .contact(new Contact().name("Demo API team")));
    }
}
