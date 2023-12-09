package com.ecommercessrfe.core.service.dto;

import java.util.Collections;

public class CartDto {

	private String email;
	private CartProducts cartProducts;

	public CartDto(String email, CartProducts cartProducts) {
		this.email = email;
		this.cartProducts = cartProducts;
	}

	public CartDto() {
	}

	public String getEmail() {
		return email;
	}

	public CartProducts getCartProducts() {
		return cartProducts;
	}

	public static CartDto empty() {
		return new CartDto("", new CartProducts(0, 0L, Collections.emptyList()));
	}
}
