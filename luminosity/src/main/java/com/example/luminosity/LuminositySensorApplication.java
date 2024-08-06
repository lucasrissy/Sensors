package com.example.luminosity;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@OpenAPIDefinition(
		info = @Info(
				title = "Temperature Microservice REST API Documentation",
				description = "Temperature sensor microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Lucas Andrade",
						email = "rissiandrade@gmail.com"
				),
				license = @License(
						name = "Apache 1.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Temperature Sensor microservice REST API Documentation",
				url = "https://www.eazybytes.com/swagger-ui.html"
		)
)
@SpringBootApplication
public class LuminositySensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuminositySensorApplication.class, args);
	}

}
