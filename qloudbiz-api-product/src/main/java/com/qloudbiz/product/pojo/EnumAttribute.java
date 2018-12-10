package com.qloudbiz.product.pojo;

import lombok.Data;

import com.qloudbiz.core.annotations.Pojo;

@Data
public class EnumAttribute {
	
	private String enumId;
	private String enumAttributeId;
	@Pojo(name = "enumValue")
	private String attrributeValues;

}
