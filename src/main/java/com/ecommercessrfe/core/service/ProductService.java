package com.ecommercessrfe.core.service;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommercessrfe.core.service.dto.ProductListDto;
import com.ecommercessrfe.core.service.dto.Response;

@Component
public class ProductService {

	private final RestClient restClient;

	public ProductService(RestClient restClient) {
		this.restClient = restClient;
	}

	public Response<ProductListDto> findByCategory(String category) {
		return restClient.get()
			.uri("https://kecommerce.shop/api/categories/" + category)
			.accept(MediaType.APPLICATION_JSON)
			.exchange((request, response) -> {
				if (response.getStatusCode().is4xxClientError()) {
					return new Response<ProductListDto>("조회된 상품 없음 ", ProductListDto.emptyList());
				}
				return response.bodyTo(new ParameterizedTypeReference<Response<ProductListDto>>() {
				});
			});
	}

	public Response<ProductListDto> findByCategoryWithPagination(String category, String page) {
		URI url = UriComponentsBuilder.newInstance()
			.scheme("https")
			.host("kecommerce.shop")
			.path("/api/categories/" + category + "/detail")
			.queryParam("page", page)
			.build()
			.toUri();
		url = url;
		return restClient.get()
			.uri(url)
			.accept(MediaType.APPLICATION_JSON)
			.exchange((request, response) -> {
				if (response.getStatusCode().is4xxClientError()) {
					return new Response<ProductListDto>("조회된 상품 없음 ", ProductListDto.emptyList());
				}
				return response.bodyTo(new ParameterizedTypeReference<Response<ProductListDto>>() {
				});
			});
	}
}
