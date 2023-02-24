package com.hzy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @title: SwaggerConfig
 * @Author zxwyhzy
 * @Date: 2023/2/24 20:24
 * @Version 1.0
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hzy.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("喵喵队", "http://www.mimi.com", "my@my.com");
        return new ApiInfoBuilder()
                .title("博客")
                .description("分享")
                .contact(contact)   // 联系方式
                .version("1.1.0")  // 版本
                .build();
    }
}
