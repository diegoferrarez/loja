package com.loja.config;

import com.loja.controller.StoreController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String SWAGGER_API_VERSION = "1.0.0";
    private static final String LICENSE_TEXT = "MIT";
    private static final String title = "store";
    private static final String description = "Serviços de ações do sistema";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(SWAGGER_API_VERSION)
                .license(LICENSE_TEXT)
                .contact(new Contact("SCTO Innovations", "www.scto.com", "scto@scto.com"))
                .build();
    }

    @Value("${app.version}")
    private String appVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .enable(true)
                .select()
                .paths(PathSelectors.any())
                .apis(basePackage(StoreController.class.getPackage().getName()))
                .build();
    }
}
