package com.ecommercessrfe.core.controller.web;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class ExceptionAdviceController {

	@ExceptionHandler(Exception.class)
	public ModelAndView redirectHomeAndErrorMessage(Exception e) {
		ModelMap model = new ModelMap();
		model.addAttribute("message", e.getMessage());
		model.addAttribute("redirectUrl", "/");
		ModelAndView modelAndView = new ModelAndView(
			new RedirectView("/message-redirect"), model);
		modelAndView.setStatus(HttpStatus.MOVED_PERMANENTLY);
		return modelAndView;
	}
}
