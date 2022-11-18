package com.demo.blogging.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	private ApiKey apiKey() {
		return new ApiKey(AUTHORIZATION_HEADER, "Authorization", "header");
	}

	
	private List<SecurityContext> securityContext(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}
	 
	private List<SecurityReference> sf(){
		
		AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("Authorization", new AuthorizationScope[] {scope}));
	}

	@Bean
	public Docket getDocket() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).securityContexts(securityContext())
				.securitySchemes(Arrays.asList(apiKey())).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo getInfo() {
		return new ApiInfo("Blogging-Application : Backend ", "This project is developed by Ganesh Patekar", "1.0",
				"Terms of Service : NA",
				new Contact("Ganesh", "https://github.com/gpganeshpatekar/blogging-app", "gpganeshpatekar@gmail.com"),
				"License of Api's ", "Api's License URL : None ", Collections.emptyList());
	}
}
