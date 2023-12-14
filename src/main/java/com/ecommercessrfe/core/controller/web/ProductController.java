package com.ecommercessrfe.core.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@CartAppendModel
	public String categories(@PathVariable("category") String category, Model model, CartModel cartModel) {
		Response<ProductListDto> response = productService.findByCategory(category);
		model.addAttribute("products", response.getEntity().getProducts());
		model.addAttribute("page", 1);
		model.addAttribute("category", category.toUpperCase());
		return "categories/test";
	}

	@GetMapping("/{category}/detail")
	@CartAppendModel
	public String page(@RequestParam("page") String page, @PathVariable("category") String category, Model model,
		CartModel cartModel) {
		Response<ProductListDto> response = productService.findByCategoryWithPagination(category, page);
		model.addAttribute("products", response.getEntity().getProducts());
		model.addAttribute("page", page);
		model.addAttribute("category", category);
		return "categories/test";
	}
}
