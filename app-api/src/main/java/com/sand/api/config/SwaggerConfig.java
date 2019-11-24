package com.sand.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-23
 **/
@Configuration
public class SwaggerConfig {

    @Bean
    Docket myApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sand.api"))
                .paths(PathSelectors.any())
                .build();

    }

    @Bean
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sand Rest API文档")
                .description("前端网址API文档")
                .version("1.0")
                .build();
    }
}
