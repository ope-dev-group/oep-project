package com.qloudbiz.product.pojo;

import lombok.Data;

/**
 * 产品SKU 属性
 * @author Administrator
 *
 */
@Data
public class ProductSkuAttribute {
	 private String skuAttributeId;
	 private String skuId;
	 private String	attributeId;
	 private String attributeName;
	 private String	enumValue;
	 private String	enumText;
}
