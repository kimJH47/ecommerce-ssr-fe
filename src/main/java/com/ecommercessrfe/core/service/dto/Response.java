package com.ecommercessrfe.core.service.dto;

public class Response<T> {

	private String message;
	private T entity;

	public Response(String message, T entity) {
		this.message = message;
		this.entity = entity;
	}

	public Response() {
	}

	public String getMessage() {
		return message;
	}

	public T getEntity() {
		return entity;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
}
