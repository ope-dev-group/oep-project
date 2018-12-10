package com.qloudbiz.product.service;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.product.pojo.Attribute;
import com.qloudbiz.product.vo.AttributeVO;
import com.qloudfin.qloudbus.reactive.Callback;

public interface AttributeService {
	
	//添加
	public void save(Callback<Attribute> callback, AttributeVO vo)throws GenericException;
	
	//删除
	public void delete(Callback<Integer> callback,AttributeVO vo)throws GenericException;
	
	//修改
	
	//查询列表
	
	//查询详情
	public void queryInfo(Callback<Attribute> callback,AttributeVO vo)throws GenericException;
	
}
