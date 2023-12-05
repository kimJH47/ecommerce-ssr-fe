package com.ecommercessrfe.core.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommercessrfe.core.service.AuthService;
import com.ecommercessrfe.core.service.dto.Response;
import com.ecommercessrfe.core.service.dto.TokenDto;

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
}
