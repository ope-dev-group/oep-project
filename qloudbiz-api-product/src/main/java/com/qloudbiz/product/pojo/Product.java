package com.qloudbiz.product.pojo;

import com.qloudbiz.core.annotations.Pojo;

import lombok.Data;

@Data
public class Product {
	@Pojo(name="product_id")
	private String productId;
	
	@Pojo(name="product_code")
	private String code;
	
	@Pojo(name="product_name")
	private String name;
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", code=" + code + ", name="
				+ name + "]";
	}
	
	
}
