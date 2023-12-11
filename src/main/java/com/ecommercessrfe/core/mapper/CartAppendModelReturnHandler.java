package com.ecommercessrfe.core.mapper;

import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;

public class CartAppendModelReturnHandler extends AbstractMessageConverterMethodProcessor {
	protected CartAppendModelReturnHandler(
		List<HttpMessageConverter<?>> converters) {
		super(converters);
	}

	protected CartAppendModelReturnHandler(List<HttpMessageConverter<?>> converters,
		ContentNegotiationManager contentNegotiationManager) {
		super(converters, contentNegotiationManager);
	}

	protected CartAppendModelReturnHandler(List<HttpMessageConverter<?>> converters, ContentNegotiationManager manager,
		List<Object> requestResponseBodyAdvice) {
		super(converters, manager, requestResponseBodyAdvice);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasMethodAnnotation(CartAppendModel.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return null;
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return returnType.hasMethodAnnotation(CartAppendModel.class);
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest) throws Exception {
		mavContainer.setRequestHandled(false);

	}
}
