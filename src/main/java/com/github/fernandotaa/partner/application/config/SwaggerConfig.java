package com.github.fernandotaa.partner.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger.web.*;

import java.text.MessageFormat;
import java.util.List;

/**
 * Configuration for Swaager.
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    /**
     * Bean creation of {@link SwaggerResourcesProvider} providing configuration v1
     * according to file:
     * - "/static/swagger/swagger-v1.yaml".
     *
     * @return - {@link SwaggerResourcesProvider}
     */
    @Bean
    @Primary
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> List.of(buildSwaggerResource("v1"));
    }

    /**
     * Bean creation of {@link UiConfiguration} where swagger UI show Paths expanded
     * and request duration.
     *
     * @return - {@link UiConfiguration}
     */
    @Bean
    public UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .docExpansion(DocExpansion.LIST)
                .build();
    }

    private SwaggerResource buildSwaggerResource(String swaggerName) {
        var swaggerFileLocation = MessageFormat.format("/swagger/swagger-{0}.yaml", swaggerName);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(swaggerName);
        swaggerResource.setLocation(swaggerFileLocation);

        return swaggerResource;
    }
}
