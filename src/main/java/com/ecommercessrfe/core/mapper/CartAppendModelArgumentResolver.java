package com.ecommercessrfe.core.mapper;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ecommercessrfe.core.service.AuthService;
import com.ecommercessrfe.core.service.CartService;
import com.ecommercessrfe.core.service.dto.CartProducts;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CartAppendModelArgumentResolver implements HandlerMethodArgumentResolver {

	private final AuthService authService;
	private final CartService cartService;

	public CartAppendModelArgumentResolver(AuthService authService, CartService cartService) {
		this.authService = authService;
		this.cartService = cartService;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasMethodAnnotation(CartAppendModel.class);
	}

	@Override
	public CartModel resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		System.out.println("model");
		CartModel model = new CartModel();
		HttpServletRequest request = getHttpServletRequest();
		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			return new CartModel();
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("token")) {
				String token = cookie.getValue();
				authService.validateToken(token);
				CartProducts cartProducts = cartService.findCartProducts(token)
					.getEntity()
					.getCartProducts();
				model.addAttribute("cart", cartProducts);
				mavContainer.mergeAttributes(model.asMap());
			}
		}
		return model;
	}

	private HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
	}
}
