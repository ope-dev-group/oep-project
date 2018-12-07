package com.qloudbiz.product.service;

import java.util.List;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.product.pojo.ProductType;
import com.qloudbiz.product.vo.ProductTypeVO;
import com.qloudfin.qloudbus.reactive.Callback;

public interface ProductTypeService {
	
	//添加
	public void add(Callback<ProductType> callback,ProductTypeVO vo)throws GenericException;
	
	//删除
	public void remote(Callback<Integer> callback,ProductTypeVO vo)throws GenericException;
	
	//修改
	public void modify(Callback<Integer> callback,ProductTypeVO vo)throws GenericException;
	
	//查询树
	public void seachTree(Callback<List<ProductType>> callback,ProductTypeVO vo)throws GenericException;
	
	//查询详情
	public void seachById(Callback<ProductType> callback,ProductTypeVO vo)throws GenericException;

}
