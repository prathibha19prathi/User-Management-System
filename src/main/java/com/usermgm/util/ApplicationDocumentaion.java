package com.usermgm.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumentaion {
//     http;//localhost:8080/swagger-ui.html
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(new Info()
				.title("User_Management_System")
				.version("v1")
				.description(
				"Spring boot project built using **RESTful** Architecture covers all the `basic CRUD opterations`\n"
					+"#### Features : \n"	
						+ "- Covers all CRUD operations\n" 
						+ "- Performed field validations\n"
						+ "- used DTOs to controll inbound and outbound data\n"));
	}
}




















// in DTO request n response body is present
