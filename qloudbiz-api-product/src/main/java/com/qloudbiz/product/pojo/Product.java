package com.qloudbiz.product.pojo;

import com.qloudbiz.core.annotations.Pojo;

import lombok.Data;

@Data
public class Product {
	@Pojo(name="productId")
	private String productId;
	
	@Pojo(name="code")
	private String code;
	
	@Pojo(name="name")
	private String name;
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", code=" + code + ", name="
				+ name + "]";
	}
	
	
}
