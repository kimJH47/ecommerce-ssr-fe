package com.ecommercessrfe.core.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommercessrfe.core.service.AuthService;
import com.ecommercessrfe.core.service.SignUpDto;
import com.ecommercessrfe.core.service.dto.Response;
import com.ecommercessrfe.core.service.dto.TokenDto;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String loginAndRedirect(@ModelAttribute LoginForm loginForm, RedirectAttributes redirectAttributes) {
		Response<TokenDto> tokenDto = authService.login(loginForm);
		redirectAttributes.addFlashAttribute("token", tokenDto.getEntity().getAccessToken());
		return "redirect:/redirect/success-login";
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

	@GetMapping("/signUp")
	public String signUp(Model model) {
		model.addAttribute("signUpForm", new SignUpForm());
		return "signUp";
	}

	@PostMapping("/signUp")
	public String signUpAndRedirect(@ModelAttribute SignUpForm signUpForm, RedirectAttributes redirectAttributes) {
		Response<SignUpDto> signUpDtoResponse = authService.signUp(signUpForm);
		return "redirect:/redirect-success-signUp";
	}

	@GetMapping("redirect-success-signUp")
	public String signUpRedirect() {
		return "home";
	}
}
