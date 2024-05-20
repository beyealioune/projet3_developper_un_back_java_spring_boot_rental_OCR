package com.rental.rental.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuration Swagger pour la documentation et la génération d'API.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Définit un groupe d'API publiques pour Swagger.
     *
     * @return GroupedOpenApi configuré pour les chemins d'API publics.
     */
        @Bean
        public GroupedOpenApi publicApi() {
            return GroupedOpenApi.builder()
                    .group("public-apis")
                    .pathsToMatch("/api/**","/images/**")
                    .build();
        }

    /**
     * Configure les métadonnées de l'API pour Swagger.
     *
     * @return OpenAPI configuré avec les informations de l'API.
     */
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("Rental API")
                            .version("1.0")
                            .description("Voici l'api rental , creer dans le cadre d'un projet OCR par Beye alioune")
                            .termsOfService("http://swagger.io/terms/")
                            .license(new License().name("Apache 2.0").url("http://springdoc.org")));
        }
}
