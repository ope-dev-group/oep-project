package com.qloudbiz.product.vo;

import java.io.Serializable;

import com.qloudbiz.core.vo.PageVO;

import lombok.Data;

@Data
public class ProductVO extends PageVO implements Serializable{
	private String productId;  //产品ID
	private String code;       //产品编号
	private String name;       //产品名称
	
}
