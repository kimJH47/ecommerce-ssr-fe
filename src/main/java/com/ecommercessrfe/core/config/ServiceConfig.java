package com.ecommercessrfe.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import com.ecommercessrfe.core.service.ProductService;
import com.ecommercessrfe.core.service.AuthService;

@Configuration
public class ServiceConfig {

	@Bean
	public ProductService productService() {
		return new ProductService(restClient());
	}

	@Bean
	public AuthService authService() {
		return new AuthService(restClient());
	}

	@Bean
	public RestClient restClient() {
		return RestClient.create();
	}
}
