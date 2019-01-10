package com.africaprudential.backenddemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
    public Docket offerPlatformAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("EasyCoop")
                .apiInfo(apiInfo())                
                .select()                
                .paths(regex("/api.*"))
                .build();
    }
	
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Easy Coop API")
                .description("Easy Coop API")
                .termsOfServiceUrl("http://www.africaprudential.com/terms-conditions")
                .version("1.0")
                .build();
    }
}
