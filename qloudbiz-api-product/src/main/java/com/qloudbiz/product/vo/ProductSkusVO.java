package com.qloudbiz.product.vo;

import java.util.List;

import lombok.Data;

import com.qloudbiz.product.pojo.ProductSku;

/**
 * 产品Skus VO
 * @author Administrator
 *
 */
@Data
public class ProductSkusVO {
	List<ProductSkuVO> skus;
}
