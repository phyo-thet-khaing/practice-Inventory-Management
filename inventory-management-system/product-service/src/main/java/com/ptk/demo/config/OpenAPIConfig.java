package com.ptk.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {
	
	
	@Bean
	public OpenAPI productServiceOpenAPI() {
		
		return new OpenAPI()
				.info(
					 new Info()
							.title("Product Service API")
							.version("1.0.0")
							.description("REST API for Product Management"));
	}
}
