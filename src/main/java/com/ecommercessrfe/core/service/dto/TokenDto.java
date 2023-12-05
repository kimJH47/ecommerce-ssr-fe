package com.ecommercessrfe.core.service.dto;

public class TokenDto {
	private String accessToken;
	private Long expireTime;
	private String type;

	public TokenDto(String accessToken, Long expireTime, String type) {
		this.accessToken = accessToken;
		this.expireTime = expireTime;
		this.type = type;
	}

	public TokenDto() {
	}

	public String getAccessToken() {
		return accessToken;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public String getType() {
		return type;
	}
}
