package com.ecommercessrfe.core.service;

import java.util.Objects;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.ecommercessrfe.core.controller.web.LoginForm;
import com.ecommercessrfe.core.controller.web.SignUpForm;
import com.ecommercessrfe.core.exception.SignUpFailedException;
import com.ecommercessrfe.core.service.dto.ErrorResponse;
import com.ecommercessrfe.core.service.dto.Response;
import com.ecommercessrfe.core.service.dto.TokenDto;

@Component
public class AuthService {
	private final RestClient restClient;

	public AuthService(RestClient restClient) {
		this.restClient = restClient;
	}

	public Response<TokenDto> login(LoginForm loginForm) {
		return restClient.post()
			.uri("https://kecommerce.shop/api/auth/token")
			.contentType(MediaType.APPLICATION_JSON)
			.body(loginForm)
			.exchange((clientRequest, clientResponse) -> {
				if (clientResponse.getStatusCode().is4xxClientError()) {
					ErrorResponse errorResponse = clientResponse.bodyTo(ErrorResponse.class);
					throw new IllegalArgumentException(errorResponse.getMessage() + "\n" + errorResponse.getReasons());
				}
				return Objects.requireNonNull(
					clientResponse.bodyTo(new ParameterizedTypeReference<Response<TokenDto>>() {
					}));
			});
	}

	public void validateToken(String value) {

	}

	public Response<SignUpDto> signUp(SignUpForm signUpForm) {
		return restClient.post()
			.uri("https://kecommerce.shop/api/auth/sign-up")
			.contentType(MediaType.APPLICATION_JSON)
			.body(signUpForm)
			.exchange((clientRequest, clientResponse) -> {
				if (clientResponse.getStatusCode().is4xxClientError()) {
					ErrorResponse errorResponse = clientResponse.bodyTo(ErrorResponse.class);
					throw new SignUpFailedException(errorResponse.getMessage() + "\n" + errorResponse.getReasons());
				}
				return Objects.requireNonNull(
					clientResponse.bodyTo(new ParameterizedTypeReference<Response<SignUpDto>>() {
					}));
			});
	}
}
