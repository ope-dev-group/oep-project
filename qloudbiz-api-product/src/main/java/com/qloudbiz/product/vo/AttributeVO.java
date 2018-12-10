package com.qloudbiz.product.vo;

import java.io.Serializable;

import lombok.Data;

import com.qloudbiz.product.pojo.Attribute;

@Data
public class AttributeVO extends Attribute implements Serializable{
	
	//分页参数
	private Integer pagePerNum=null;//pageSize,页大小
	private Integer currentNum=null;//当前页
}
