package com.ecommercessrfe.core.service.dto;

import java.util.Collections;
import java.util.List;

public class CartProducts {
	private int count;
	private Long totalPrice;
	private List<CartProductDto> value;

	public CartProducts(int count, Long totalPrice, List<CartProductDto> value) {
		this.count = count;
		this.totalPrice = totalPrice;
		this.value = value;
	}

	public int getCount() {
		return count;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public List<CartProductDto> getValue() {
		return value;
	}

	public static CartProducts empty() {
		return new CartProducts(0, 0L, Collections.emptyList());
	}
}
