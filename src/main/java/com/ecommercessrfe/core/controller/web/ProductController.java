package com.ecommercessrfe.core.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercessrfe.core.mapper.CartAppendModel;
import com.ecommercessrfe.core.mapper.CartModel;
import com.ecommercessrfe.core.service.ProductService;
import com.ecommercessrfe.core.service.dto.ProductListDto;
import com.ecommercessrfe.core.service.dto.Response;

@Controller
@RequestMapping("/categories")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{category}")
	public String categories(@PathVariable("category") String category, Model model) {
		Response<ProductListDto> response = productService.findByCategory(category);
		model.addAttribute("products", response.getEntity().getProducts());
		return "categories/categories-view";
	}

	@GetMapping("/test")
	@CartAppendModel
	public String test(Model model, CartModel cartModel) {
		Response<ProductListDto> response = productService.findByCategory("TOP");
		model.addAttribute("products", response.getEntity().getProducts());
		return "categories/test";
	}
}
