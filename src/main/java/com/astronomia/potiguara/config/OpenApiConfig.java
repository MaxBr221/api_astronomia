package com.astronomia.potiguara.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Astronomia Indígena Potiguara & Guia Stellarium")
                        .version("1.0.0")
                        .description("Serviços RESTful para consulta de constelações ancestrais Potiguaras, " +
                                "parâmetros de observação no Stellarium, exercícios interativos e progresso de aprendizado.")
                        .contact(new Contact()
                                .name("Projeto Astronomia Indígena Potiguara")
                                .url("https://github.com"))
                        .license(new License().name("Apache 2.0")));
    }
}
