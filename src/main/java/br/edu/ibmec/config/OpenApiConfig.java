package br.edu.ibmec.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Universidade Spring API")
                        .version("1.0.0")
                        .description("API completa para gerenciamento de universidade com padrões de design")
                );
    }
}
