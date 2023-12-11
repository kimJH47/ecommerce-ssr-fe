package com.ecommercessrfe.core.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercessrfe.core.mapper.CartAppendModel;
import com.ecommercessrfe.core.mapper.CartModel;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	@CartAppendModel
	public String home(CartModel cartModel) {
		return "home";
	}

	@GetMapping("redirect/success-login")
	public String successLoginRedirect(@ModelAttribute("token") String token, HttpServletResponse httpServletResponse) {
		httpServletResponse.addCookie(createTokenCookie(token));
		return "redirect:/";
	}

	private Cookie createTokenCookie(String token) {
		Cookie cookie = new Cookie("token", token);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 60 * 3); //3 hour
		cookie.setPath("/");
		return cookie;
	}
}
