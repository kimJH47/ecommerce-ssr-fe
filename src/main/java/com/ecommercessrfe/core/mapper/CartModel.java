package com.ecommercessrfe.core.mapper;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.Conventions;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class CartModel {

	private Map<String, Object> attributes = new LinkedHashMap<>();

	public CartModel addAttribute(String attributeName, @Nullable Object attributeValue) {
		Assert.notNull(attributeName, "Model attribute name must not be null");
		attributes.put(attributeName, attributeValue);
		return this;
	}

	public CartModel addAttribute(Object attributeValue) {
		if (attributeValue instanceof Collection<?> collection && collection.isEmpty()) {
			return this;
		}
		return addAttribute(Conventions.getVariableName(attributeValue), attributeValue);
	}

	public CartModel addAllAttributes(@Nullable Collection<?> attributeValues) {
		if (attributeValues != null) {
			for (Object attributeValue : attributeValues) {
				addAttribute(attributeValue);
			}
		}
		return this;
	}

	public CartModel addAllAttributes(@Nullable Map<String, ?> attributes) {
		if (attributes != null) {
			this.attributes.putAll(attributes);
		}
		return this;
	}

	public CartModel mergeAttributes(@Nullable Map<String, ?> attributes) {
		if (attributes != null) {
			attributes.forEach((key, value) -> {
				if (!attributes.containsKey(key)) {
					this.attributes.put(key, value);
				}
			});
		}
		return this;
	}

	public boolean containsAttribute(String attributeName) {
		return false;
	}

	public Object getAttribute(String attributeName) {
		return null;
	}

	public Map<String, Object> asMap() {
		return attributes;
	}

}
