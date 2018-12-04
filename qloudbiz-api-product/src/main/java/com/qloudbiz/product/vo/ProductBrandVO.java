package com.qloudbiz.product.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import com.qloudbiz.core.vo.PageVO;
import com.qloudbiz.product.pojo.ProductBrand;

/**
 * 产品品牌VO
 * @author Administrator
 *
 */
@Data
public class ProductBrandVO extends ProductBrand implements Serializable{
	
	//分页参数
	private Integer pagePerNum=null;//pageSize,页大小
	private Integer currentNum=null;//当前页
}
