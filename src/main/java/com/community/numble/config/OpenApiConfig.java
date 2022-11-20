package com.community.numble.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String API_NAME = "Numble Backend API";
    private static final String API_VERSION = "v1.0.0";
    private static final String API_DESCRIPTION = "Numble 프로젝트 API 명세서";

    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
            .title(API_NAME)
            .version(API_VERSION)
            .description(API_DESCRIPTION);

        return new OpenAPI()
            .components(new Components())
            .info(info);
    }

}
