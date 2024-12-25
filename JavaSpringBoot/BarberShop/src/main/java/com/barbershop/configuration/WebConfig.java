package com.barbershop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**") // Allow all API endpoints
				.allowedOrigins("http://localhost:4200") // Allow Angular frontend
				.allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific methods
				.allowedHeaders("Content-Type", "Authorization"); // Allow specific headers
	}
}