package com.ecommercessrfe.core.service;

public class SignUpDto {
	private String email;
	private String requestTime;

	public SignUpDto(String email, String requestTime) {
		this.email = email;
		this.requestTime = requestTime;
	}

	public String getEmail() {
		return email;
	}

	public String getRequestTime() {
		return requestTime;
	}
}
