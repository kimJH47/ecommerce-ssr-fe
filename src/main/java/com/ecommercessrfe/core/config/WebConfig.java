package com.ecommercessrfe.core.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ecommercessrfe.core.mapper.CartAppendModelArgumentResolver;
import com.ecommercessrfe.core.service.AuthService;
import com.ecommercessrfe.core.service.CartService;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	private final CartService cartService;
	private final AuthService authService;

	public WebConfig(CartService cartService, AuthService authService) {
		this.cartService = cartService;
		this.authService = authService;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(0, new CartAppendModelArgumentResolver(authService, cartService));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
	}
}
