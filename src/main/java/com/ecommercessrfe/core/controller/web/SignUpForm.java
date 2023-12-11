package com.ecommercessrfe.core.controller.web;

public class SignUpForm {
	private String email;
	private String password;

	public SignUpForm(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public SignUpForm() {
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
