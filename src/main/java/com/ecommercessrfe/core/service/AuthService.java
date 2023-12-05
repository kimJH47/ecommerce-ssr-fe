package com.ecommercessrfe.core.service;

import java.util.Objects;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.ecommercessrfe.core.controller.web.LoginForm;
import com.ecommercessrfe.core.service.dto.Response;
import com.ecommercessrfe.core.service.dto.TokenDto;

@Component
public class AuthService {
	private final RestClient restClient;

	public AuthService(RestClient restClient) {
		this.restClient = restClient;
	}

	public TokenDto login(String email, String password) {

		restClient.post()
			.uri("https://kecommerce.shop/api/auth/token")
			.contentType(MediaType.APPLICATION_JSON);
		return null;
	}

	public Response<TokenDto> login(LoginForm loginForm) {
		return restClient.post()
			.uri("https://kecommerce.shop/api/auth/token")
			.contentType(MediaType.APPLICATION_JSON)
			.body(loginForm)
			.exchange((clientRequest, clientResponse) -> {
				if (clientResponse.getStatusCode().is4xxClientError()) {
					throw new IllegalArgumentException("로그인 과정에서 에러발생");
				}
				return Objects.requireNonNull(
					clientResponse.bodyTo(new ParameterizedTypeReference<Response<TokenDto>>() {
					}));
			});
	}

	public void validateToken(String value) {

	}
}
