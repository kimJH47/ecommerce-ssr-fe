package com.ecommercessrfe.core.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("brandName")
	private String brandName;
	@JsonProperty("price")
	private Long price;
	@JsonProperty("category")
	private String category;

	public ProductDto(Long id, String name, String brandName, Long price, String category) {
		this.id = id;
		this.name = name;
		this.brandName = brandName;
		this.price = price;
		this.category = category;
	}

	public ProductDto() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBrandName() {
		return brandName;
	}

	public Long getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}
}
