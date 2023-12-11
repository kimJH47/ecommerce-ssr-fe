package com.ecommercessrfe.core.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercessrfe.core.mapper.CartAppendModel;
import com.ecommercessrfe.core.mapper.CartModel;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	@CartAppendModel
	public String home(CartModel cartModel) {
		return "home";
	}

	@GetMapping("/message-redirect")
	public String messageRedirect(@ModelAttribute("message") String message,
		@ModelAttribute("redirectUrl") String redirectUrl, Model model) {
		model.addAttribute("message", message);
		model.addAttribute("redirectUrl", redirectUrl);
		return "messageRedirect";
	}
}
