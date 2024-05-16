package com.rental.rental.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

        @Bean
        public GroupedOpenApi publicApi() {
            return GroupedOpenApi.builder()
                    .group("public-apis")
                    .pathsToMatch("/api/**","/images/**")
                    .build();
        }

        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("Votre API")
                            .version("1.0")
                            .description("Description de votre API")
                            .termsOfService("http://swagger.io/terms/")
                            .license(new License().name("Apache 2.0").url("http://springdoc.org")));
        }
}
