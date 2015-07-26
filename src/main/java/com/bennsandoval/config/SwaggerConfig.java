package com.bennsandoval.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by Mackbook on 7/25/15.
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan({"com.bennsandoval.api.controller"})
public class SwaggerConfig {

    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(regex("/api/v1.*"))
                .build()
                .directModelSubstitute(XMLGregorianCalendar.class, Date.class)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Amazon Example API")
                .description("API to manage a bucket on Amazon")
                .contact("bennsandoval@gmail.com")
                .version("1.0.0")
                .build();
    }

}
