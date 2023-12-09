package com.ecommercessrfe.core.service.dto;

public class CartProductDto {
	private Long id;
	private String name;
	private String brandName;
	private Long price;
	private String category;
	private int quantity;

	public CartProductDto(Long id, String name, String brandName, Long price, String category, int quantity) {
		this.id = id;
		this.name = name;
		this.brandName = brandName;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}
}
