package com.ecommercessrfe.core.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercessrfe.core.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HomeController {

	private final AuthService authService;

	public HomeController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping
	public String home(HttpServletRequest httpServletRequest, HttpServletResponse response, Model model) {
		if (model.containsAttribute("token")) {
			String token = (String)model.getAttribute("token");
			Cookie cookie = new Cookie("token", token);
			cookie.setHttpOnly(true);
			cookie.setMaxAge(60 * 60 * 3); //3 hour
			response.addCookie(cookie);
			model.addAttribute("token", null);
			return "home";
		}

		Cookie[] cookies = httpServletRequest.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("token")) {
				authService.validateToken(cookie.getValue());
			}
		}
		return "home";
	}

	@GetMapping("redirect/success-login")
	public String successLoginRedirect(@ModelAttribute("token") String token, Model model) {
		model.addAttribute("token", token);
		return "redirect:/";
	}
}
