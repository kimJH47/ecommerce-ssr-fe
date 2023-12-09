package com.ecommercessrfe.core.service;

import java.util.Objects;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.ecommercessrfe.core.service.dto.CartDto;
import com.ecommercessrfe.core.service.dto.Response;

@Component
public class CartService {

	private final RestClient restClient;

	public CartService(RestClient restClient) {
		this.restClient = restClient;
	}

	public Response<CartDto> findCartProducts(String token) {
		return restClient.get()
			.uri("https://kecommerce.shop/api/cart")
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
			.exchange((clientRequest, clientResponse) -> {
				if (clientResponse.getStatusCode().is4xxClientError()) {
					return new Response<CartDto>("사용자 정보가 존재하지 않습니다.", CartDto.empty());
				}
				return Objects.requireNonNull(
					clientResponse.bodyTo(new ParameterizedTypeReference<Response<CartDto>>() {
					}));
			});
	}
}
