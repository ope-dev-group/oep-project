package com.qloudbiz.product.vo;

import java.io.Serializable;

import com.qloudbiz.product.pojo.ProductLine;

import lombok.Data;

@Data
public class ProductLineVO extends ProductLine implements Serializable{
	
	//分页参数
	private Integer pagePerNum=null;//pageSize,页大小
	private Integer currentNum=null;//当前页
}
