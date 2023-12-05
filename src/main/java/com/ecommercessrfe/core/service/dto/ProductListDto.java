package com.ecommercessrfe.core.service.dto;

import java.util.Collections;
import java.util.List;

public class ProductListDto {

	private int totalCount;
	private List<ProductDto> products;

	public ProductListDto(int totalCount, List<ProductDto> products) {
		this.totalCount = totalCount;
		this.products = products;
	}

	public ProductListDto() {
	}

	public int getTotalCount() {
		return totalCount;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public static ProductListDto emptyList() {
		return new ProductListDto(0, Collections.emptyList());
	}
}
